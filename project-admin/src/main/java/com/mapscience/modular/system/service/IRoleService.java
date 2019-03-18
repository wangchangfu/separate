package com.mapscience.modular.system.service;

import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.Role;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-03-05
 */
public interface IRoleService extends IService<Role> {

    /**
     * 添加角色
     * @param rule
     */
    ResponseVal addRole(Role rule);

    /**
     * 分配菜单
     * @param ruleId
     * @param menuId
     */
    void distrMenu(String ruleId, String menuId);
}
