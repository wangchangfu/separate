package com.mapscience.modular.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.core.datascope.DataScope;
import com.mapscience.modular.system.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 修改用户状态
     */
    int setStatus(@Param("userId") String userId, @Param("status") int status);

    /**
     * 修改密码
     */
    int changePwd(@Param("userId") String userId, @Param("pwd") String pwd);


    /**
     * 设置用户的角色
     */
    int setRoles(@Param("userId") String userId, @Param("roleIds") String roleIds);

    /**
     * 通过账号获取用户
     */
    User getByAccount(@Param("account") String account);

    /**
     * 分页查询
     *
     * @param page
     * @param user
     * @return
     */
    List<User> getUserList(Page<User> page, User user);


    List<User> getUser();


    User getUserById(@Param("userId") String userId);

    /**
     * 根据条件查询用户列表
     */
    List<Map<String, Object>> selectUsers(Page<User> page, @Param("dataScope") DataScope dataScope, @Param("name") String name, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("deptid") String deptid);

}