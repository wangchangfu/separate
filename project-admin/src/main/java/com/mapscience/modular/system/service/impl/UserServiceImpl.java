package com.mapscience.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.common.constant.state.ManagerStatus;
import com.mapscience.core.util.AesCipherUtil;
import com.mapscience.core.util.ObjectUtil;
import com.mapscience.modular.system.dto.UserDTO;
import com.mapscience.modular.system.mapper.UserMapper;
import com.mapscience.modular.system.model.User;
import com.mapscience.modular.system.service.IUserRoleService;
import com.mapscience.modular.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    @Autowired
    private IUserRoleService userRoleService;
    /**
     * 添加
     * @param user
     * @return
     */
    @Override
    @Transactional
    public ResponseVal addUser(UserDTO user) {
        String sr="";
        String userId="";
        try{
            User u=new User();
            u.setUsername(user.getUsername());
            u.setEmpId(user.getEmpId());
            u.setComId(user.getComId());
            u.setPassword(AesCipherUtil.enCrypto(user.getUsername()+user.getPassword()));
            u.setStatus(ManagerStatus.OK.getCode());
            u.setCreateTime(new Date());
            u.setUpdateTime(new Date());
            this.baseMapper.addUser(u);
             userId = u.getUserId();
            //保存到关系表
            sr = this.userRoleService.addUserRole(userId, user.getRoleId());
            if(ObjectUtil.isEmpty(sr)){
                this.baseMapper.delectById(userId);
                return new ResponseVal(HttpStatus.INTERNAL_SERVER_ERROR.value(),"保存出错");
            }
            return new ResponseVal(0,"保存成功");
        }catch (Exception e){
            /*this.baseMapper.delectById(userId);
            this.userRoleService.deleteByRoleId(sr);*/
           return new ResponseVal(HttpStatus.INTERNAL_SERVER_ERROR.value(),"保存出错",e.getMessage());
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
    public List<User> findByComUser(User user) {

        List<User> byComUser = this.baseMapper.findByComUser(user);
        return byComUser;
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
