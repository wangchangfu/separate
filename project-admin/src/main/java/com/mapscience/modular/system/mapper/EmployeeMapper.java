package com.mapscience.modular.system.mapper;

import com.mapscience.modular.system.model.Employee;
import com.baomidou.mybatisplus.mapper.BaseMapper;

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
}
