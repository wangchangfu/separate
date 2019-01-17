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
    @Override
    public Employee getEmployeeByAccountAndPasswd(String account, String passWord) {
        return this.employeeMapper.getEmployeeByAccountAndPasswd(account, passWord);
    }
}
