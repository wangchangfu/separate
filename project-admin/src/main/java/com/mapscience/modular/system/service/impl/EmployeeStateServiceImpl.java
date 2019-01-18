package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.model.EmployeeState;
import com.mapscience.modular.system.mapper.EmployeeStateMapper;
import com.mapscience.modular.system.service.IEmployeeStateService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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

}
