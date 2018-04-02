package com.quzhigang.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quzhigang.mapper.PlatformJkLoanApplyMapper;
import com.quzhigang.model.PlatformJkLoanApplyBean;
import com.quzhigang.service.PlatformJkLoanApplyService;

@Service
public class PlatformJkLoanApplyServiceImpl implements PlatformJkLoanApplyService {

	@Autowired
	private PlatformJkLoanApplyMapper platformJkLoanApplyMapper;

	@Override
	public PlatformJkLoanApplyBean queryForApplyNo(String applyNo) {
		return platformJkLoanApplyMapper.queryByApplyNo(applyNo);
	}

	@Override
	public void insertOrUpdatePlatformJkLoanApply(PlatformJkLoanApplyBean platformJkLoanApplyBean) {
		if (platformJkLoanApplyBean.getId() > 0) { // 修改
			platformJkLoanApplyMapper.update(platformJkLoanApplyBean);
		} else { // 新增
			platformJkLoanApplyMapper.insert(platformJkLoanApplyBean);
		}
	}

}
