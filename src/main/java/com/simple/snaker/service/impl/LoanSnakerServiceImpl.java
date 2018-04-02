package com.simple.snaker.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.snaker.engine.access.Page;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Task;
import org.snaker.engine.entity.WorkItem;
import org.snaker.engine.model.TaskModel.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.quzhigang.model.PlatformJkLoanApplyBean;
import com.quzhigang.service.PlatformJkLoanApplyService;
import com.simple.snaker.service.LoanProceeRunStateEnum;
import com.simple.snaker.service.LoanSnakerService;

@Service
public class LoanSnakerServiceImpl implements LoanSnakerService {

	private static Log systemLog = LogFactory.getLog("System");

	@Autowired
	private SnakerEngineFacets snakerEngineFacets;

	@Autowired
	private PlatformJkLoanApplyService platformJkLoanApplyService;

	@Override
	public void StartProcess(String applyNo, String employeeId, Map<String, Object> map) {
		try {
			// 初始化流程
			String processId = snakerEngineFacets.initFlows2();

			// 流程定义ID
			// String processId = "7ff3ca84b7834f2db8b2e9c17914594c";

			// 任务ID
			String taskId = "";

			// 定义流程变量,snaker在启动流程之前必须把所有流程节点的所有操作员放入map中
			map.put("applyNo", applyNo); // 定义流程和业务关联数据
			map.put("salesman_apply.operator", LoanProceeRunStateEnum.SALESMAN_APPLY.getRoleName()); // 借款业务员申请操作角色
			map.put("risk_audit.operator", LoanProceeRunStateEnum.RISK_AUDIT.getRoleName()); // 借款风控审核操作角色
			map.put("product_audit.operator", LoanProceeRunStateEnum.PRODUCT_AUDIT.getRoleName()); // 借款产品审核操作角色
			map.put("product_operation_audit.operator", LoanProceeRunStateEnum.PRODUCT_OPERATION_AUDIT.getRoleName()); // 借款产品运营操作角色

			// 启动流程
			Order startAndExecute = snakerEngineFacets.startAndExecute(processId,
					LoanProceeRunStateEnum.SALESMAN_APPLY.getRoleName(), map);
			systemLog.info("借款编号：" + applyNo + " 流程启动成功！");

			// 更新业务表数据【tbl_platform_jk_loan_apply】
			List<Task> activeTasks = snakerEngineFacets.getEngine().query()
					.getActiveTasks(new QueryFilter().setOrderId(startAndExecute.getId()));
			if (activeTasks != null && activeTasks.size() > 0) {
				Task task = activeTasks.get(0);
				taskId = task.getId();
				PlatformJkLoanApplyBean platformJkLoanApplyBean = platformJkLoanApplyService.queryForApplyNo(applyNo);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				platformJkLoanApplyBean.setApplyNo(applyNo);
				platformJkLoanApplyBean.setTaskId(taskId);
				platformJkLoanApplyBean.setProcessOrderId(startAndExecute.getId());
				platformJkLoanApplyBean
						.setRunState(LoanProceeRunStateEnum.queryForProcessState(task.getTaskName()).getState());
				platformJkLoanApplyBean
						.setRoleName(LoanProceeRunStateEnum.queryForProcessState(task.getTaskName()).getRoleName());
				platformJkLoanApplyBean.setOperateUser(employeeId);
				platformJkLoanApplyBean.setCreateTime(sdf.format(new Date()));
				platformJkLoanApplyService.insertOrUpdatePlatformJkLoanApply(platformJkLoanApplyBean);
			}

		} catch (Exception e) {
			systemLog.info("借款编号:" + applyNo + "启动借款流程失败");
			systemLog.error(e.getMessage());
		}

	}

	@Override
	public List<WorkItem> queryProcessList(String employeeId) {
		Page<WorkItem> page = new Page<WorkItem>();
		String[] array = employeeId.split(",");
		String[] assignees = new String[]{employeeId};
		return snakerEngineFacets.getEngine().query().getWorkItems(page, new QueryFilter().setOperators(array).setTaskType(TaskType.Major.ordinal()));
	}

	@Override
	public void RunProcess(String taskId, String applyNo, String employeeId, String roleName, Map<String, Object> map,
			boolean approve, String nodeName) {
		try {
			if(StringUtils.isEmpty(taskId)){
				systemLog.info("taskId 不能为空!");
				return;
			}
			
			List<Task>  tasks = null;
			//任务ID
			String task_no = "";
			//任务名称
			String taskName = "";
			if(approve){ //同意
				tasks = snakerEngineFacets.execute(taskId, roleName, map);
			}else{ //退回
				tasks = snakerEngineFacets.executeAndJump(taskId, roleName, map, nodeName);
			}
			if( tasks != null && tasks.size() >0 ){
				Task task = tasks.get(0);
				task_no = task.getId();
				taskName = task.getTaskName();
			}
			//更新业务表数据 【tbl_platform_jk_loan_apply】
			PlatformJkLoanApplyBean platformJkLoanApplyBean = platformJkLoanApplyService.queryForApplyNo(applyNo);
			platformJkLoanApplyBean.setTaskId(StringUtils.isEmpty(task_no) ? "" : task_no);
			//如果任务编号为空，就设置RunState为【 流程结束】
			platformJkLoanApplyBean.setRunState(StringUtils.isEmpty(task_no) ? LoanProceeRunStateEnum.COMPLETE.getState() 
					: LoanProceeRunStateEnum.queryForProcessState(taskName).getState());
			//如果任务编号为空，就设置RoleName为【 流程结束】
			platformJkLoanApplyBean.setRoleName(StringUtils.isEmpty(task_no) ? LoanProceeRunStateEnum.COMPLETE.getRoleName()
					: LoanProceeRunStateEnum.queryForProcessState(taskName).getRoleName());
			platformJkLoanApplyBean.setOperateUser(employeeId);
			platformJkLoanApplyService.insertOrUpdatePlatformJkLoanApply(platformJkLoanApplyBean);
		} catch (Exception e) {
			e.printStackTrace();
			systemLog.info("借款编号: "+applyNo+"流程流转失败！ taskId: "+taskId);
			systemLog.error(e.getMessage());
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
