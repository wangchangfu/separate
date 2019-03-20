package com.mapscience.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mapscience.modular.system.mapper.UserRoleMapper;
import com.mapscience.modular.system.model.UserRole;
import com.mapscience.modular.system.service.IUserRoleService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

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

    /**
     * 添加
     * @param s
     * @param roleId
     */
    @Override
    public String addUserRole(String s, String roleId) {
        try{
            UserRole u=new UserRole();
            u.setCreateTime(new Date());
            u.setUpdateTime(new Date());
            u.setRoleId(roleId);
            u.setUserId(s);
            u.setId(UUID.randomUUID().toString().replace("-",""));
            this.baseMapper.addUserRole(u);
            return u.getId();
        }catch (Exception e){
            return null;
        }


    }

    @Override
    public void deleteByRoleId(String id) {
        this.baseMapper.deleteByRoleId(id);
    }
}
