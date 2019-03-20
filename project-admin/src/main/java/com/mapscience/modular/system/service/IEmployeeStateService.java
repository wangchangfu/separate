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

    EmployeeState getEmployeeStateByName(String employeeStateName);

}
