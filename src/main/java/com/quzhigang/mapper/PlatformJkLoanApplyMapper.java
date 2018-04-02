package com.quzhigang.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;

import com.quzhigang.model.CityModel;
import com.quzhigang.model.PlatformJkLoanApplyBean;

@MapperScan
public interface PlatformJkLoanApplyMapper {

	String columns ="id,apply_no,task_id,process_order_id,run_state,role_name,operate_user,create_time,update_time";
	
	String insert = "apply_no,task_id,process_order_id,run_state,role_name,operate_user,update_time";
	
	String insertProperty = "#{applyNo},#{taskId},#{processOrderId},#{runState},#{roleName},#{operateUser},now(),#{updateTime}";
	
	String update = "apply_no=#{applyNo},task_id=#{taskId},process_order_id=#{processOrderId},run_state=#{runState},role_name=#{roleName},operate_user=#{operateUser},update_time=#{updateTime}";

	@Select("select "+columns+" from tbl_platform_jk_loan_apply where 1=1 and apply_no= #{applyNo}")
	@ResultMap(value="com.quzhigang.mapper.PlatformJkLoanApplyMapper.PlatformJkLoanApplyBeanMap")
	public PlatformJkLoanApplyBean queryByApplyNo(String applyNo);
	
	
	@Insert("insert into tbl_platform_jk_loan_apply("+insert+") values ("+insertProperty+")")
	@Options(useGeneratedKeys=true, keyProperty="id")
	public long insert(PlatformJkLoanApplyBean platformJkLoanApplyBean);
	
	@Update("update tbl_platform_jk_loan_apply set "+update+" where 1=1 and apply_no = #{applyNo}")
	public long update(PlatformJkLoanApplyBean platformJkLoanApplyBean);

}
