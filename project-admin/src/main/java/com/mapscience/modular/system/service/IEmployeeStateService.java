package com.mapscience.modular.system.service;

import com.mapscience.modular.system.model.EmployeeState;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 员工状态类型表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
public interface IEmployeeStateService extends IService<EmployeeState> {

    /**
     * 根据人员状态名称查找
     *
     * @param employeeStateName
     * @return
     */
    EmployeeState getEmployeeStateByName(String employeeStateName);

    /**
     * 根据人员状态id查找
     *
     * @param employeeStatuId
     * @return
     */
    EmployeeState getEmployeeStateById(String employeeStatuId);

}
