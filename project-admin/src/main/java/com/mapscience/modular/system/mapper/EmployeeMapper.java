

package com.mapscience.modular.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    /**
     * 模糊查询
     * @return
     */
    List<Employee> fuzzyQuery(@Param("comId")String comId, @Param("empName")String empName, @Param("tel")String tel,
                              @Param("starWorkTime")String starWorkTime, @Param("endWorkTime")String endWorkTime,
                              @Param("startBirthTime")String startBirthTime, @Param("endBirthTime")String endBirthTime,
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
    
    List<Employee> findEmployee(@Param("employee")Employee employee, @Param("companyId")String companyId);



    /**
     * 查询所有员工
     *
     * @return
     */
    List<Employee> getList();
    /**
     * 根据员工姓名查询
     *
     * @param employeeName
     * @return
     */
    Employee getEmployeeByEmployeeName(String employeeName);
  

   


    /**
     * 根据身份证号查找
     *
     * @param cardId
     * @return
     */
    Employee getEmployeeByCardId(String cardId);


}

