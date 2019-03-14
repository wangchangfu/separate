package com.mapscience.modular.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mapscience.modular.system.dto.UserDTO;
import com.mapscience.modular.system.model.User;

import java.util.List;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-03-05
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 添加
     * @param user
     * @return
     */
    void addUser(User user);

    /**
     * 查找账号是否存在
     * @param accect
     * @return
     */
    User getByAccount(String accect);

    /**
     * 按照公司查找
     * @return
     */
    List<User> findByComUser(UserDTO user);

    /**
     * 根据ID查询管理
     * @param id
     * @return
     */

    User getById(String id);
}
