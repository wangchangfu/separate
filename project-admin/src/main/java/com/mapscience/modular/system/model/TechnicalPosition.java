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
 * 专业技术职务表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@TableName("t_technical_position")
public class TechnicalPosition extends Model<TechnicalPosition> {

    private static final long serialVersionUID = 1L;

    /**
     * uuid主键
     */
    @TableId("technical_position_id")
    @Excel(name = "id")
    private String technicalPositionId;
    /**
     * 专业技术职务名称
     */
    @TableField("technical_position_name")
    @Excel(name = "专业技术职务")
    private String technicalPositionName;
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


    public String getTechnicalPositionId() {
        return technicalPositionId;
    }

    public void setTechnicalPositionId(String technicalPositionId) {
        this.technicalPositionId = technicalPositionId;
    }

    public String getTechnicalPositionName() {
        return technicalPositionName;
    }

    public void setTechnicalPositionName(String technicalPositionName) {
        this.technicalPositionName = technicalPositionName;
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
        return this.technicalPositionId;
    }

    @Override
    public String toString() {
        return "TechnicalPosition{" +
        "technicalPositionId=" + technicalPositionId +
        ", technicalPositionName=" + technicalPositionName +
        ", status=" + status +
        ", crateTime=" + crateTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
