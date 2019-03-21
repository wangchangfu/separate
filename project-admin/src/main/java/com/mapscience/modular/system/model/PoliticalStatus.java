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
 * 政治面貌表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@TableName("t_political_status")
public class PoliticalStatus extends Model<PoliticalStatus> {

    private static final long serialVersionUID = 1L;

    /**
     * uuid主键
     */
    @TableId("political_status_id")
    @Excel(name = "id")
    private String politicalStatusId;
    /**
     * 政治面貌名称
     */
    @TableField("political_status_name")
    @Excel(name = "政治面貌代码")
    private String politicalStatusName;
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


    public String getPoliticalStatusId() {
        return politicalStatusId;
    }

    public void setPoliticalStatusId(String politicalStatusId) {
        this.politicalStatusId = politicalStatusId;
    }

    public String getPoliticalStatusName() {
        return politicalStatusName;
    }

    public void setPoliticalStatusName(String politicalStatusName) {
        this.politicalStatusName = politicalStatusName;
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
        return this.politicalStatusId;
    }

    @Override
    public String toString() {
        return "PoliticalStatus{" +
        "politicalStatusId=" + politicalStatusId +
        ", politicalStatusName=" + politicalStatusName +
        ", status=" + status +
        ", crateTime=" + crateTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
