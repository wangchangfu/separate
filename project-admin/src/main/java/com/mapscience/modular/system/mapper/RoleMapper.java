package com.mapscience.modular.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mapscience.modular.system.model.Role;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-03-05
 */
public interface RoleMapper extends BaseMapper<Role> {


    /**
     * 添加角色
     * @param rule
     */
    void addRole(Role rule);

    /**
     * 根据管理员ID查找角色
     * @param userId
     */
    @Select("select r.* from t_role r left join t_user_role u on u.role_id=r.role_id and r.status=1 where u.user_id=#{userId}")
    Role findByRoleId(String userId);

}
