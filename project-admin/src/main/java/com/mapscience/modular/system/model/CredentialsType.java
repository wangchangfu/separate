package com.mapscience.modular.system.model;

import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 证件类型表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@TableName("t_credentials_type")
public class CredentialsType extends Model<CredentialsType> {

    private static final long serialVersionUID = 1L;

    /**
     * uuid
     */
    @TableId("credentials_stype_id")
    @Excel(name = "id")
    private String credentialsStypeId;
    /**
     * 证件类型名称
     */
    @TableField("credentials_stype_name")
    @Excel(name = "证件类型")
    private String credentialsStypeName;
    /**
     * 状态0是删除1是启用2是增加不显示查询显示
     */
    private Integer status;
    /**
     * 创建时间
     */
    @TableField("crate_time")
    private Date crateTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;


    public String getCredentialsStypeId() {
        return credentialsStypeId;
    }

    public void setCredentialsStypeId(String credentialsStypeId) {
        this.credentialsStypeId = credentialsStypeId;
    }

    public String getCredentialsStypeName() {
        return credentialsStypeName;
    }

    public void setCredentialsStypeName(String credentialsStypeName) {
        this.credentialsStypeName = credentialsStypeName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.credentialsStypeId;
    }

    @Override
    public String toString() {
        return "CredentialsType{" +
        "credentialsStypeId=" + credentialsStypeId +
        ", credentialsStypeName=" + credentialsStypeName +
        ", status=" + status +
        ", crateTime=" + crateTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
