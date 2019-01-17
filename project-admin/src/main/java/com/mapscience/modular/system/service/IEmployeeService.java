package com.mapscience.modular.system.service;

import com.mapscience.modular.system.model.Employee;
import com.baomidou.mybatisplus.service.IService;

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
     * @param account
     * @param passWord
     * @return
     */
    Employee getEmployeeByAccountAndPasswd(String account, String passWord);
}
