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
 * 民族表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@TableName("t_nation_type")
public class NationType extends Model<NationType> {

    private static final long serialVersionUID = 1L;

    /**
     * uuid主键
     */
    @TableId("nation_stype_id")
    @Excel(name = "id")
    private String nationStypeId;
    /**
     * 民族名称
     */
    @TableField("nation_stype_name")
    @Excel(name = "民族")
    private String nationStypeName;
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


    public String getNationStypeId() {
        return nationStypeId;
    }

    public void setNationStypeId(String nationStypeId) {
        this.nationStypeId = nationStypeId;
    }

    public String getNationStypeName() {
        return nationStypeName;
    }

    public void setNationStypeName(String nationStypeName) {
        this.nationStypeName = nationStypeName;
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
        return this.nationStypeId;
    }

    @Override
    public String toString() {
        return "NationType{" +
        "nationStypeId=" + nationStypeId +
        ", nationStypeName=" + nationStypeName +
        ", status=" + status +
        ", crateTime=" + crateTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
