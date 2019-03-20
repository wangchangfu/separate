package com.mapscience.modular.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mapscience.modular.system.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

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
    List<User> findByComUser(User user);

    /**
     * 根据ID查询管理
     * @param id
     * @return
     */

    User getById(String id);

    /**
     * 根据Id删除
     * @param userId
     */
    @Delete("delete from t_user where userId=#{userId}")
    void delectById(@Param("userId") String userId);
}
