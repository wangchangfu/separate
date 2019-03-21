package com.mapscience.modular.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.mapscience.modular.system.model.UserRole;

/**
 * <p>
 * 管理员角色表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-03-05
 */
public interface IUserRoleService extends IService<UserRole> {

    UserRole findByEmp(String employeeId);

    /**
     * 添加关联
     * @param s
     * @param roleId
     */
    String addUserRole(String s, String roleId);


    void deleteByRoleId(String id);
}
