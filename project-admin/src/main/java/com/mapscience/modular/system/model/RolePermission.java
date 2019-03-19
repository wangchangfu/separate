package com.mapscience.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 角色资源表
 * </p>
 *
 * @author ${author}
 * @since 2019-03-05
 */
@TableName("t_role_permission")
public class RolePermission extends Model<RolePermission> {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 角色ID
     */
    @TableField("role_id")
    private String roleId;
    /**
     * 菜单ID
     */
    @TableField("menu_id")
    private String menuId;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    public RolePermission() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "RolePermission{" +
        "id=" + id +
        ", roleId=" + roleId +
        ", menuId=" + menuId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }

}
