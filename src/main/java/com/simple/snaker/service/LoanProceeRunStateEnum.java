/**
 * Project Name:yahao-p2p
 * File Name:LoanProceeRunStateEnum.java
 * Package Name:com.midai.engine.support
 * Date:2018年3月17日上午10:26:15
 *
*/

package com.simple.snaker.service;
/**
 * ClassName:LoanProceeRunStateEnum <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年3月17日 上午10:26:15 <br/>
 * @author   屈志刚  
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public enum LoanProceeRunStateEnum {
	
	SALESMAN_APPLY(11,"salesman_apply","SALESMAN_APPLY"),
	RISK_AUDIT(12,"risk_audit","RISK_AUDIT"),
	PRODUCT_AUDIT(13,"product_audit","PRODUCT_AUDIT"),
	PRODUCT_OPERATION_AUDIT(14,"product_operation_audit","PRODUCT_OPERATION_AUDIT"),
	COMPLETE(15,"complete","COMPLETE"),
	COLSE_PROCESS(-1,"close_process","COLSE_PROCESS");
	
	
	
	private int state;
	private String processState; //流程节点name
	private String roleName; //操作人
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getProcessState() {
		return processState;
	}
	public void setProcessState(String processState) {
		this.processState = processState;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	private LoanProceeRunStateEnum(int state, String processState,
			String roleName) {
		this.state = state;
		this.processState = processState;
		this.roleName = roleName;
	}
	
	public static LoanProceeRunStateEnum queryForProcessState(String processState){
		
		LoanProceeRunStateEnum[] enums = values();
		
		for(LoanProceeRunStateEnum e : enums){
			
			if(e.getProcessState().equals(processState)){
				return e;
			}
		}
		
		return null;
	}
	
	
	
	

}

