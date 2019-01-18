package com.mapscience.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 健康状况表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@TableName("t_health")
public class Health extends Model<Health> {

    private static final long serialVersionUID = 1L;

    /**
     * uuid主键
     */
    @TableId("health_id")
    private String healthId;
    /**
     * 健康状况名称
     */
    @TableField("health_name")
    private String healthName;
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


    public String getHealthId() {
        return healthId;
    }

    public void setHealthId(String healthId) {
        this.healthId = healthId;
    }

    public String getHealthName() {
        return healthName;
    }

    public void setHealthName(String healthName) {
        this.healthName = healthName;
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
        return this.healthId;
    }

    @Override
    public String toString() {
        return "Health{" +
        "healthId=" + healthId +
        ", healthName=" + healthName +
        ", status=" + status +
        ", crateTime=" + crateTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
