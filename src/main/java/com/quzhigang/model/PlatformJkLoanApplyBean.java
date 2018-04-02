package com.quzhigang.model;

public class PlatformJkLoanApplyBean {

	private long id;
	private String applyNo;  //'借款编号',
	private String taskId;  //工作流任务编号
	private String processOrderId;  //工作流流程实例ID
	private int runState;  //'流程状态（11:业务员申请，12：风控审核，13：产品审核，14：产品运营，15：流程完毕）',
	private String roleName;  //'对应可以查看的角色',
	private String operateUser;  //'操作人'
	private String createTime;
	private String updateTime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getApplyNo() {
		return applyNo;
	}
	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getProcessOrderId() {
		return processOrderId;
	}
	public void setProcessOrderId(String processOrderId) {
		this.processOrderId = processOrderId;
	}
	public int getRunState() {
		return runState;
	}
	public void setRunState(int runState) {
		this.runState = runState;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getOperateUser() {
		return operateUser;
	}
	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
}
