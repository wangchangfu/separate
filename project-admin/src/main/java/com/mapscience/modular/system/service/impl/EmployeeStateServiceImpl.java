package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.model.EmployeeState;
import com.mapscience.modular.system.mapper.EmployeeStateMapper;
import com.mapscience.modular.system.service.IEmployeeStateService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工状态类型表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@Service
public class EmployeeStateServiceImpl extends ServiceImpl<EmployeeStateMapper, EmployeeState> implements IEmployeeStateService {

    @Autowired
    private EmployeeStateMapper employeeStateMapper;


    /**
     * 根据人员状态名称查找
     *
     * @param employeeStateName
     * @return
     */
    @Override
    public EmployeeState getEmployeeStateByName(String employeeStateName) {
        return employeeStateMapper.getEmployeeStateByName(employeeStateName);
    }

    /**
     * 根据人员状态id查找
     *
     * @param employeeStatuId
     * @return
     */
    @Override
    public EmployeeState getEmployeeStateById(String employeeStatuId) {
        return this.baseMapper.getEmployeeStateById(employeeStatuId);
    }
}
