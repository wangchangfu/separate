package com.mapscience.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 管理员角色表
 * </p>
 *
 * @author ${author}
 * @since 2019-03-05
 */
@TableName("t_user_role")
public class UserRole extends Model<UserRole> {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 管理员ID
     */
    @TableField("user_id")
    private String userId;
    /**
     * 角色ID
     */
    @TableField("role_id")
    private String roleId;
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
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
        return "UserRole{" +
        "id=" + id +
        ", userId=" + userId +
        ", roleId=" + roleId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
