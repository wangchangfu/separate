
package com.mapscience.modular.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.util.ObjectUtil;
import com.mapscience.modular.system.mapper.EmployeeMapper;
import com.mapscience.modular.system.model.Company;
import com.mapscience.modular.system.model.Employee;
import com.mapscience.modular.system.service.IEmployeeService;

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


    /**
     * 根据公司统计人员信息
     *
     * @param company
     * @return
     */
    @Override
    public ResponseVal getEmpCount(Company company) {
        if (ObjectUtil.isEmpty(company.getCompanyId())) {
            company.setCompanyId("1");
        }
        Integer empCount = this.baseMapper.getEmpCount(company.getCompanyId());
        if (empCount < 0) {
            return new ResponseVal(HttpStatus.FOUND.value(), "暂无数据", 0);
        }
        return new ResponseVal(HttpStatus.OK.value(), "查询成功", empCount);
    }

    @Override
    public List<Employee> getEmployeeByCompanyId(String companyId) {
        return employeeMapper.getEmployeeByCompanyId(companyId);
    }

	/**
     * 查询所有员工
     *
     * @return
     */
    @Override
    public List<Employee> getList() {
        return employeeMapper.getList();
    }


    /**
     * 根据员工姓名查询
     *
     * @param employeeName
     * @return
     */
    @Override
    public Employee getEmployeeByEmployeeName(String employeeName) {
        return employeeMapper.getEmployeeByEmployeeName(employeeName);
    }


    /**
     * 根据身份证号查找员工
     *
     * @param cardId
     * @return
     */
    @Override
    public Employee getEmployeeByCardId(String cardId) {
        return employeeMapper.getEmployeeByCardId(cardId);
    }

	@Override
	public ArrayList<HashMap<String, String>> employeeAgeDistributionMap(String companyId, String ageRange, String gender) {
		String[] ageRangeArr = ageRange.split(",");
		String[] genderArr = gender.split(",");
		ArrayList<HashMap<String, String>> newArrayListForReturn = Lists.newArrayList();
		for(String ge : genderArr) {
			HashMap<String, String> newHashMap = Maps.newHashMap();
			newHashMap.put("性别", ge);
			for (String ag : ageRangeArr) {
				String[] split = ag.split("-");
				HashMap<String, String> params = Maps.newHashMap();
				params.put("companyId", companyId);
				params.put("gender", ge);
				params.put("beginAge", split[0]);
				params.put("endAge", split[1]);
				newHashMap.put(ag, String.valueOf(employeeMapper.findEmployee(params).size()));
			}
			newArrayListForReturn.add(newHashMap);
		}
		return newArrayListForReturn;
	}

}

