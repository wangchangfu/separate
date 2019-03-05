package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.model.Employee;
import com.mapscience.modular.system.mapper.EmployeeMapper;
import com.mapscience.modular.system.service.IEmployeeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工信息表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 用户名密码查询用户
     * @param account
     * @param password
     * @return
     */
    @Override
    public Employee getEmployeeByAccountAndPasswd(String account, String password) {
        return this.employeeMapper.getEmployeeByAccountAndPasswd(account, password);
    }

    /**
     * 通过账号获取用户
     * @return
     */
    @Override
    public Employee getByAccount(Employee e) {
        return this.baseMapper.getByAccount(e.getAccount());
    }

    /**
     * 添加用户
     *  em
     */
    @Override
    public void add(Employee em) {

    }


}
