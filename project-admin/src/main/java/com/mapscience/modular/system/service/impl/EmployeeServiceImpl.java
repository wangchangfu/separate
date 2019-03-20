package com.mapscience.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.util.ObjectUtil;
import com.mapscience.modular.system.mapper.EmployeeMapper;
import com.mapscience.modular.system.model.Company;
import com.mapscience.modular.system.model.Employee;
import com.mapscience.modular.system.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public List<Employee> fuzzyQuery(String comId, String empName, String tel, String starWorkTime, String endWorkTime, String startBirthTime, String endBirthTime, String education) {
        return employeeMapper.fuzzyQuery(comId, empName, tel, starWorkTime, endWorkTime, startBirthTime, endBirthTime, education);
    }

    @Override
    public void add(Employee em) {

    }


    @Transactional
	@Override
	public void batchDeleteEmployeeStatusByIds(String ids) {
		String[] split = ids.split(",");
		for (String string : split) {
			employeeMapper.deleteEmployeeStatusById(string);
		}
	}

    @Override
    public List<Employee> getEmployeeByCompanyId(String companyId) {
        return employeeMapper.getEmployeeByCompanyId(companyId);
    }

	@Override
	public List<Employee> findEmployeeByCompanyId(String companyId) {
		return employeeMapper.findEmployee(null, companyId);
	}


}
