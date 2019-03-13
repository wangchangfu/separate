package com.mapscience.modular.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mapscience.modular.system.model.Employee;

/**
 * <p>
 * 员工信息表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * 根据账号查询用户
     * @param account
     * @return
     */
    Employee getByAccount(String account);

    /**
     * 根据账户号和密码查询用户信息
     * @param account
     * @param passWord
     * @return
     */
    Employee getEmployeeByAccountAndPasswd(String account, String passWord);
    
    /**
     * 模糊查询
     * @return
     */
    List<Employee> fuzzyQuery(@Param("comId")String comId,@Param("empName")String empName,@Param("tel")String tel,
    		@Param("starWorkTime")String starWorkTime,@Param("endWorkTime")String endWorkTime,
    		@Param("startBirthTime")String startBirthTime,@Param("endBirthTime")String endBirthTime,
    		@Param("education")String education);
    
    /**
     * 通过公司id查询员工
     * @param companyId
     * @return
     */
    List<Employee> getEmployeeByCompanyId(String companyId);
    
    /**
     * 通过员工id删除员工状态
     * @param id
     * @return
     */
    int deleteEmployeeStatusById(String id);
    
}
