package com.mapscience.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 公司业务类型关系表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@TableName("t_company_type_re")
public class CompanyTypeRe extends Model<CompanyTypeRe> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId("company_type_re_id")
    private String companyTypeReId;
    /**
     * 业务类型id
     */
    @TableField("company_type_id")
    private String companyTypeId;
    /**
     * 公司id
     */
    @TableField("company_id")
    private String companyId;
    /**
     * 0是禁用（相当于删除这个必须没有被使用才能禁用）1是启用
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


    public String getCompanyTypeReId() {
        return companyTypeReId;
    }

    public void setCompanyTypeReId(String companyTypeReId) {
        this.companyTypeReId = companyTypeReId;
    }

    public String getCompanyTypeId() {
        return companyTypeId;
    }

    public void setCompanyTypeId(String companyTypeId) {
        this.companyTypeId = companyTypeId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
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
        return this.companyTypeReId;
    }

    @Override
    public String toString() {
        return "CompanyTypeRe{" +
        "companyTypeReId=" + companyTypeReId +
        ", companyTypeId=" + companyTypeId +
        ", companyId=" + companyId +
        ", status=" + status +
        ", crateTime=" + crateTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
