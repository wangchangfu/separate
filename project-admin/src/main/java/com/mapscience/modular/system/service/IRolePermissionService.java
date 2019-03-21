package com.mapscience.modular.system.service;

import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.RolePermission;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 角色资源表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-03-05
 */
public interface IRolePermissionService extends IService<RolePermission> {

    /**
     * 为角色分配菜单
     * @param roleId
     * @param menuId
     */
    ResponseVal distrMenu(String roleId, String menuId);
}
