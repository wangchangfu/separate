package com.mapscience.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 公司权力机构类型
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@TableName("t_company_director_type")
public class CompanyDirectorType extends Model<CompanyDirectorType> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId("company_director_type_id")
    private String companyDirectorTypeId;
    /**
     * 类型名
     */
    @TableField("company_director_name")
    private String companyDirectorName;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 状态 0是禁用 1是启用
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


    public String getCompanyDirectorTypeId() {
        return companyDirectorTypeId;
    }

    public void setCompanyDirectorTypeId(String companyDirectorTypeId) {
        this.companyDirectorTypeId = companyDirectorTypeId;
    }

    public String getCompanyDirectorName() {
        return companyDirectorName;
    }

    public void setCompanyDirectorName(String companyDirectorName) {
        this.companyDirectorName = companyDirectorName;
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
        return this.companyDirectorTypeId;
    }

    @Override
    public String toString() {
        return "CompanyDirectorType{" +
        "companyDirectorTypeId=" + companyDirectorTypeId +
        ", companyDirectorName=" + companyDirectorName +
        ", sort=" + sort +
        ", status=" + status +
        ", crateTime=" + crateTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
