package com.mapscience.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author ${author}
 * @since 2019-03-05
 */
@TableName("t_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId("user_id")
    private String userId;
    /**
     * 账号 
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * MD5密码盐
     */
    private String salt;
    /**
     * 员工ID
     */
    @TableField("emp_id")
    private String empId;
    /**
     * 员工姓名
     */
    private String empName;
    /**
     * 状态 1 启动 2 冻结 3 删除
     */
    private Integer status;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 公司id
     */
    @TableField("com_id")
    private String comId;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    private String token;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        return this.userId;
    }

    @Override
    public String toString() {
        return "User{" +
        "userId=" + userId +
        ", account=" + username +
        ", password=" + password +
        ", salt=" + salt +
        ", empId=" + empId +
        ", status=" + status +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }

    public User() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public User(String userId, String account, String password, String salt, String empId, Integer status, Date createTime, Date updateTime) {
        this.userId = userId;
        this.username = account;
        this.password = password;
        this.salt = salt;
        this.empId = empId;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
