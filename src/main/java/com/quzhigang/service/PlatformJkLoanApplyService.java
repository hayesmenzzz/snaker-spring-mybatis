package com.quzhigang.service;

import com.quzhigang.model.PlatformJkLoanApplyBean;

public interface PlatformJkLoanApplyService {

	PlatformJkLoanApplyBean queryForApplyNo(String applyNo);
	
	void insertOrUpdatePlatformJkLoanApply(PlatformJkLoanApplyBean platformJkLoanApplyBean);
}
