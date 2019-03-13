package com.mapscience.modular.system.mapper;

import com.mapscience.modular.system.model.UserRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 管理员角色表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-03-05
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    @Select("select * from t_user_role where emp_id=#{employeeId}")
    UserRole findByEmp(String employeeId);
}
