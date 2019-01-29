package com.mapscience.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 登录记录
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@TableName("t_login_log")
public class LoginLog extends Model<LoginLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 日志名称
     */
    private String logname;
    /**
     * 管理员id
     */
    private String userid;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 是否执行成功
     */
    private String succeed;
    /**
     * 具体消息
     */
    private String message;
    /**
     * 登录ip
     */
    private String ip;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogname() {
        return logname;
    }

    public void setLogname(String logname) {
        this.logname = logname;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getSucceed() {
        return succeed;
    }

    public void setSucceed(String succeed) {
        this.succeed = succeed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "LoginLog{" +
        "id=" + id +
        ", logname=" + logname +
        ", userid=" + userid +
        ", createtime=" + createtime +
        ", succeed=" + succeed +
        ", message=" + message +
        ", ip=" + ip +
        "}";
    }
}
