package com.mapscience.modular.system.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mapscience.core.datascope.DataScope;
import com.mapscience.modular.system.mapper.UserMapper;
import com.mapscience.modular.system.model.User;
import com.mapscience.modular.system.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public int setStatus(String userId, int status) {
        return this.baseMapper.setStatus(userId, status);
    }

    @Override
    public int changePwd(String userId, String pwd) {
        return this.baseMapper.changePwd(userId, pwd);
    }


    @Override
    public int setRoles(String userId, String roleIds) {
        return this.baseMapper.setRoles(userId, roleIds);
    }

    @Override
    public User getByAccount(String account) {
        return this.baseMapper.getByAccount(account);
    }

    @Override
    public List<User> getUserList(Page<User> page, User user) {
        return this.baseMapper.getUserList(page, user);
    }

    @Override
    public List<User> getUser() {
        return baseMapper.getUser();
    }

    @Override
    public User getUserById(String userId) {
        return this.baseMapper.getUserById(userId);
    }

    @Override
    public List<Map<String, Object>> selectUsers(Page<User> page, DataScope dataScope, String name, String beginTime, String endTime, String deptid) {
        return this.baseMapper.selectUsers(page, dataScope, name, beginTime, endTime, deptid);
    }
}
