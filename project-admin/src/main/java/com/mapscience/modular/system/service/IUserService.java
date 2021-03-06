package com.mapscience.modular.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.dto.UserDTO;
import com.mapscience.modular.system.model.User;

import java.util.List;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-03-05
 */
public interface IUserService extends IService<User> {

    /**
     * 添加
     * @param user
     */
    ResponseVal addUser(UserDTO user);

    /**
     * 查询用户是否存在
     *
     * @return
     */
    User getByAccount(String account);

    /**
     * 按照公司查找管理员
     * @param user
     * @return
     */
    List<User> findByComUser(User user);

    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    User getById(String id);

    /**
     * 按照管理员ID查找管理员
     * @param user
     * @return
     */
    ResponseVal findByIdUser(User user);
}
