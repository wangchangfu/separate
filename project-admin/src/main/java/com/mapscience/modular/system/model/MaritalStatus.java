package com.mapscience.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 婚姻状况表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@TableName("t_marital_status")
public class MaritalStatus extends Model<MaritalStatus> {

    private static final long serialVersionUID = 1L;

    /**
     * uuid主键
     */
    @TableId("marital_status_id")
    private String maritalStatusId;
    /**
     * 婚姻状况名称
     */
    @TableField("marital_status_name")
    private String maritalStatusName;
    /**
     * 0是删除1是启用2是增加不显示查询显示
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


    public String getMaritalStatusId() {
        return maritalStatusId;
    }

    public void setMaritalStatusId(String maritalStatusId) {
        this.maritalStatusId = maritalStatusId;
    }

    public String getMaritalStatusName() {
        return maritalStatusName;
    }

    public void setMaritalStatusName(String maritalStatusName) {
        this.maritalStatusName = maritalStatusName;
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
        return this.maritalStatusId;
    }

    @Override
    public String toString() {
        return "MaritalStatus{" +
        "maritalStatusId=" + maritalStatusId +
        ", maritalStatusName=" + maritalStatusName +
        ", status=" + status +
        ", crateTime=" + crateTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
