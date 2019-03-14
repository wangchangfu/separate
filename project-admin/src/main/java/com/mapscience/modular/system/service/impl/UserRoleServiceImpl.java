package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.model.UserRole;
import com.mapscience.modular.system.mapper.UserRoleMapper;
import com.mapscience.modular.system.service.IUserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员角色表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-03-05
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Override
    public UserRole findByEmp(String employeeId) {
        return this.baseMapper.findByEmp(employeeId);
    }
}
