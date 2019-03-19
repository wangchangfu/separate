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
 * 国籍表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@TableName("t_nationality_type")
public class NationalityType extends Model<NationalityType> {

    private static final long serialVersionUID = 1L;

    /**
     * uuid主键
     */
    @TableId("nationality_stype_id")
    @Excel(name = "id")
    private String nationalityStypeId;
    /**
     * 国籍名称
     */
    @TableField("nationality_stype_name")
    @Excel(name = "国家地区")
    private String nationalityStypeName;
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


    public String getNationalityStypeId() {
        return nationalityStypeId;
    }

    public void setNationalityStypeId(String nationalityStypeId) {
        this.nationalityStypeId = nationalityStypeId;
    }

    public String getNationalityStypeName() {
        return nationalityStypeName;
    }

    public void setNationalityStypeName(String nationalityStypeName) {
        this.nationalityStypeName = nationalityStypeName;
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
        return this.nationalityStypeId;
    }

    @Override
    public String toString() {
        return "NationalityType{" +
        "nationalityStypeId=" + nationalityStypeId +
        ", nationalityStypeName=" + nationalityStypeName +
        ", status=" + status +
        ", crateTime=" + crateTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
