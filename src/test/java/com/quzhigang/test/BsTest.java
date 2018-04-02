package com.quzhigang.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.snaker.engine.entity.WorkItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.quzhigang.model.CityModel;
import com.quzhigang.service.CityService;
import com.quzhigang.service.PlatformJkLoanApplyService;
import com.simple.snaker.service.LoanSnakerService;
import com.simple.snaker.service.LoanUserApproveEnum;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
//@ContextConfiguration({"file:src/main/resources/spring-*.xml"}) 
public class BsTest {
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private LoanSnakerService loanSnakerService;
	
	@Autowired
	private PlatformJkLoanApplyService platformJkLoanApplyService;
	
	@SuppressWarnings("deprecation")
	@Test
	public void test(){
		
//		CityModel cityModel = new CityModel();
//		cityModel.setId(120000);
//		cityModel.setName("天津市");
//		cityModel.setPid(0);
//		cityModel.setFirst_letter("T");
//		cityModel.setPinyin("Tian Jin");
//		
//		Assert.assertEquals(1, cityService.insert(cityModel)); 
	}
	
	@Test
	public void testSnaker(){
		
		/*INSERT INTO `tbl_system_role` (`roleID`, `roleName`, `discription`) VALUES ('11', 'SALESMAN_APPLY', '借款业务员申请');
		INSERT INTO `tbl_system_role` (`roleID`, `roleName`, `discription`) VALUES ('12', 'RISK_AUDIT', '借款风控审核');
		INSERT INTO `tbl_system_role` (`roleID`, `roleName`, `discription`) VALUES ('13', 'PRODUCT_AUDIT', '借款产品审核');
		INSERT INTO `tbl_system_role` (`roleID`, `roleName`, `discription`) VALUES ('14', 'PRODUCT_OPERATION_AUDIT', '借款产品运营');*/
		
		String applyNo = UUID.randomUUID().toString();
		System.out.println("applyNo:  "+applyNo);
		Map<String,Object> map = new HashMap<String, Object>();
		List<WorkItem> templist = null;
		
		//================================正常流程  START===============================
		//业务员申请
		loanSnakerService.StartProcess(applyNo, "salesman_apply",  map);
		
		//================================【风控审核】退回至【业务员申请】  START===============================
		//风控审核通过
//		templist = loanSnakerService.queryProcessList("RISK, 12, risk");
//		if(templist != null && templist.size() > 0){
//			WorkItem workItem = templist.get(0);
//			map = new HashMap<>();
//			map.put("result", LoanUserApproveEnum.getRoleId(12, false));
//			loanSnakerService.RunProcess(workItem.getTaskId(), "risk",null, applyNo, map, false, "product_audit");
//			map.clear();
//		}
		
		
	}

}
