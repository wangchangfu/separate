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
 * 教育类型表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@TableName("t_record_type")
public class RecordType extends Model<RecordType> {

    private static final long serialVersionUID = 1L;

    /**
     * uuid
     */
    @TableField("record_type_id")
    @Excel(name = "id")
    private String recordTypeId;
    /**
     * 类型名称(全日制，自考，函授等)
     */
    @TableField("record_type_name")
    @Excel(name = "学习方式")
    private String recordTypeName;
    /**
     * 状态 :0是删除1是启用2是增加不显示查询显示
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


    public String getRecordTypeId() {
        return recordTypeId;
    }

    public void setRecordTypeId(String recordTypeId) {
        this.recordTypeId = recordTypeId;
    }

    public String getRecordTypeName() {
        return recordTypeName;
    }

    public void setRecordTypeName(String recordTypeName) {
        this.recordTypeName = recordTypeName;
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
        return this.recordTypeId;
    }

    @Override
    public String toString() {
        return "RecordType{" +
        "recordTypeId=" + recordTypeId +
        ", recordTypeName=" + recordTypeName +
        ", status=" + status +
        ", crateTime=" + crateTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
