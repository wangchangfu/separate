package com.mapscience.modular.system.service.impl;

import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.util.ObjectUtil;
import com.mapscience.modular.system.model.Company;
import com.mapscience.modular.system.model.Employee;
import com.mapscience.modular.system.mapper.EmployeeMapper;
import com.mapscience.modular.system.service.IEmployeeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    /**
     * 根据公司统计人员信息
     * @param company
     * @return
     */
    @Override
    public ResponseVal getEmpCount(Company company) {
        if(ObjectUtil.isEmpty(company.getCompanyId())){
            company.setCompanyId("1");
        }
        Integer empCount = this.baseMapper.getEmpCount(company.getCompanyId());
        if (empCount <0){
            return new ResponseVal(HttpStatus.FOUND.value(),"暂无数据",0);
        }
        return new ResponseVal(HttpStatus.OK.value(),"查询成功",empCount);
    }


}
