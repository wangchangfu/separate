package com.mapscience.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.dto.UserDTO;
import com.mapscience.modular.system.mapper.UserMapper;
import com.mapscience.modular.system.model.User;
import com.mapscience.modular.system.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-03-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    /**
     * 添加
     * @param user
     * @return
     */
    @Override
    public ResponseVal addUser(User user) {
        try{
             this.baseMapper.addUser(user);

            return new ResponseVal(200,"保存成功");
        }catch (Exception e){
           return new ResponseVal(500,"",e);
        }


    }

    /**
     * 查询管理员是否存在
     *
     * @return
     */
    @Override
    public User getByAccount(String account) {
        User us = this.baseMapper.getByAccount(account);
        return us;
    }

    /**
     * 按照公司查找管理员
     * @param user
     * @return
     */
    @Override
    public List<UserDTO> findByComUser(UserDTO user) {

        this.baseMapper.findByComUser(user);
        return null;
    }

    /**
     * 根据ID查询管理员
     * @param id
     * @return
     */
    @Override
    public User getById(String id) {
        User byId = this.baseMapper.getById(id);
        return byId;
    }
}
