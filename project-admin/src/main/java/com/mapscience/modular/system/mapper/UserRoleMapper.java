package com.mapscience.modular.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mapscience.modular.system.model.UserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
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


    void addUserRole(UserRole u);


    @Delete("delete from t_user_role where userId=#{id}")
    void deleteByRoleId(@Param("id") String id);
}
