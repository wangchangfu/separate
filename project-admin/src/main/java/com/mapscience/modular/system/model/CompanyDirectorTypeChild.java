package com.mapscience.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 公司权力机构类型子类型表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@TableName("t_company_director_type_child")
public class CompanyDirectorTypeChild extends Model<CompanyDirectorTypeChild> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId("company_director_type_child_id")
    private String companyDirectorTypeChildId;
    /**
     * 名称
     */
    @TableField("company_director_child_name")
    private String companyDirectorChildName;
    /**
     * 权力机构类型id
     */
    @TableField("company_director_type_id")
    private String companyDirectorTypeId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 状态 0禁用 1启用
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


    public String getCompanyDirectorTypeChildId() {
        return companyDirectorTypeChildId;
    }

    public void setCompanyDirectorTypeChildId(String companyDirectorTypeChildId) {
        this.companyDirectorTypeChildId = companyDirectorTypeChildId;
    }

    public String getCompanyDirectorChildName() {
        return companyDirectorChildName;
    }

    public void setCompanyDirectorChildName(String companyDirectorChildName) {
        this.companyDirectorChildName = companyDirectorChildName;
    }

    public String getCompanyDirectorTypeId() {
        return companyDirectorTypeId;
    }

    public void setCompanyDirectorTypeId(String companyDirectorTypeId) {
        this.companyDirectorTypeId = companyDirectorTypeId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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
        return this.companyDirectorTypeChildId;
    }

    @Override
    public String toString() {
        return "CompanyDirectorTypeChild{" +
        "companyDirectorTypeChildId=" + companyDirectorTypeChildId +
        ", companyDirectorChildName=" + companyDirectorChildName +
        ", companyDirectorTypeId=" + companyDirectorTypeId +
        ", sort=" + sort +
        ", status=" + status +
        ", crateTime=" + crateTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
