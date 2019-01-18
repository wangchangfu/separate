package com.mapscience.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 员工学历字典表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@TableName("t_education_type")
public class EducationType extends Model<EducationType> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId("education_type_id")
    private String educationTypeId;
    /**
     * 学历名称
     */
    @TableField("education_type_name")
    private String educationTypeName;
    /**
     * 状态
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


    public String getEducationTypeId() {
        return educationTypeId;
    }

    public void setEducationTypeId(String educationTypeId) {
        this.educationTypeId = educationTypeId;
    }

    public String getEducationTypeName() {
        return educationTypeName;
    }

    public void setEducationTypeName(String educationTypeName) {
        this.educationTypeName = educationTypeName;
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
        return this.educationTypeId;
    }

    @Override
    public String toString() {
        return "EducationType{" +
        "educationTypeId=" + educationTypeId +
        ", educationTypeName=" + educationTypeName +
        ", status=" + status +
        ", crateTime=" + crateTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
