package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.model.Department;
import com.mapscience.modular.system.mapper.DepartmentMapper;
import com.mapscience.modular.system.service.IDepartmentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

}
