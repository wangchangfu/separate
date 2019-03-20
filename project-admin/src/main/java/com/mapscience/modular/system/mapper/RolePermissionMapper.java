package com.mapscience.modular.system.mapper;

import com.mapscience.modular.system.model.RolePermission;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 角色资源表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-03-05
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    /**
     * 分配菜单
     */
    void distrMenu(RolePermission rolePermission);
}
