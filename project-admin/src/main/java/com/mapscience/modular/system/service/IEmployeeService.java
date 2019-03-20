package com.mapscience.modular.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.Company;
import com.mapscience.modular.system.model.Employee;

import java.util.List;

/**
 * <p>
 * 员工信息表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
public interface IEmployeeService extends IService<Employee> {

    /**
     * 通过登录账户和密码查找用户信息

     * @return
     */
    Employee getEmployeeByAccountAndPasswd(String account, String password);

    /**
     * 通过账号获取用
     * @return
     */
    Employee getByAccount(Employee e);

    /**
     * 模糊查询
     */
    List<Employee> fuzzyQuery(String comId, String empName, String tel, String starWorkTime, String endWorkTime, String startBirthTime, String endBirthTime, String education);

    /**
     * 通过公司id查询员工
     */
    void add(Employee em);

    
    List<Employee> getEmployeeByCompanyId(String companyId);

    /**
     * 通过ids批量删除员工状态
     */
    void batchDeleteEmployeeStatusByIds(String ids);
    
    List<Employee> findEmployeeByCompanyId(String companyId);

}
