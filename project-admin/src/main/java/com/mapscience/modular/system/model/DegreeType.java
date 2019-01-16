package com.mapscience.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 员工学位字典表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@TableName("t_degree_type")
public class DegreeType extends Model<DegreeType> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId("degree_type_id")
    private String degreeTypeId;
    /**
     * 名称
     */
    @TableField("degree_type_name")
    private String degreeTypeName;
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


    public String getDegreeTypeId() {
        return degreeTypeId;
    }

    public void setDegreeTypeId(String degreeTypeId) {
        this.degreeTypeId = degreeTypeId;
    }

    public String getDegreeTypeName() {
        return degreeTypeName;
    }

    public void setDegreeTypeName(String degreeTypeName) {
        this.degreeTypeName = degreeTypeName;
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
        return this.degreeTypeId;
    }

    @Override
    public String toString() {
        return "DegreeType{" +
        "degreeTypeId=" + degreeTypeId +
        ", degreeTypeName=" + degreeTypeName +
        ", status=" + status +
        ", crateTime=" + crateTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
