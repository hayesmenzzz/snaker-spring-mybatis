package com.simple.snaker.service;

import java.util.List;
import java.util.Map;

import org.snaker.engine.entity.WorkItem;

public interface LoanSnakerService {

	/**
	 * 启动流程
	 * @param applyNo
	 * @param employeeId
	 * @param map
	 */
	void StartProcess(String applyNo,String employeeId,Map<String,Object> map);
	
	/**
	 * 获取待审任务
	 * @param employeeId
	 * @return
	 */
	List<WorkItem> queryProcessList(String employeeId);
	
	/**
	 * 流程流转
	 * @param taskId
	 * @param applyNo
	 * @param employeeId 操作人
	 * @param roleName
	 * @param map
	 * @param approve 审核结果 true同意  false拒绝
	 * @param nodeName  流程节点名称
	 */
	void RunProcess(String taskId, String applyNo, String employeeId, String roleName, Map<String, Object> map, boolean approve, String nodeName);
}
