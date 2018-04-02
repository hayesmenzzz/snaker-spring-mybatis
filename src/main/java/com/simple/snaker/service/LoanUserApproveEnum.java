/**
 * Project Name:yahao-p2p
 * File Name:LoanUserApproveEnum.java
 * Package Name:com.midai.engine.support
 * Date:2018年3月16日上午9:36:21
 *
*/

package com.simple.snaker.service;
/**
 * ClassName:LoanUserApproveEnum <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年3月16日 上午9:36:21 <br/>
 * @author   屈志刚  
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public enum LoanUserApproveEnum {
	
	RISK_AGREE(12,"risk_agree",true),
	RISK_DISAGREE(12,"risk_disagree",false),
	PRODUCT_AGREE(13,"product_agree",true),
	PRODUCT_DISAGREE(13,"product_disagree",false),
	PRODUCT_OPERATION_AGREE(14,"product_operation_agree",true),
	PRODUCT_OPERATION_DISAGREE(14,"product_operation_disagree",false);
	
	
	private int roleId;
	private String approveStr;
	private boolean approveFlag;
	
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getApproveStr() {
		return approveStr;
	}

	public void setApproveStr(String approveStr) {
		this.approveStr = approveStr;
	}
	
	

	public boolean isApproveFlag() {
		return approveFlag;
	}

	public void setApproveFlag(boolean approveFlag) {
		this.approveFlag = approveFlag;
	}

	
	private LoanUserApproveEnum(int roleId, String approveStr,
			boolean approveFlag) {
		this.roleId = roleId;
		this.approveStr = approveStr;
		this.approveFlag = approveFlag;
	}

	public static LoanUserApproveEnum getRoleId(int roleId, boolean approveFlag){
		LoanUserApproveEnum[] enums = values();
		
		for(LoanUserApproveEnum e: enums){
			
			if(roleId == e.getRoleId() && approveFlag == e.isApproveFlag()){
				return e;
			}
		}
		return null;
		
	}
	
	
	
	
	
	
	
	
	


}

