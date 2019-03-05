package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.model.Role;
import com.mapscience.modular.system.mapper.RoleMapper;
import com.mapscience.modular.system.service.IRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-03-05
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
