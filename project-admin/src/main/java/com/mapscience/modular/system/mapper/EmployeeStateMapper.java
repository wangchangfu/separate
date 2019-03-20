package com.mapscience.modular.system.mapper;

import com.mapscience.modular.system.model.EmployeeState;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 员工状态类型表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
public interface EmployeeStateMapper extends BaseMapper<EmployeeState> {

    EmployeeState getEmployeeStateByName(String employeeStateName);

}
