package com.mapscience.modular.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mapscience.modular.system.model.Employee;
import org.apache.ibatis.annotations.Select;

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
     * 根据账号查询用户
     *
     * @return
     */
    @Select("select * from t_employee where employee_id=#{empId}")
    Employee getByEmpId(String empId);

    /**
     * 根据公司统计人员数量
     * @param companyId
     * @return
     */
    @Select("SELECT count(DISTINCT(e.employee_id)) as count from t_employee e  JOIN t_emp_position ecd  on e.employee_id=ecd.emp_id  where ecd.com_id=#{companyId}")
    Integer getEmpCount(String companyId);
}
