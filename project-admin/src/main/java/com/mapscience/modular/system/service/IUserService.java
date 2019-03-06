package com.mapscience.modular.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.User;

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
    ResponseVal addUser(User user);

    /**
     * 查询用户是否存在
     *
     * @return
     */
    User getByAccount(String account);
}
