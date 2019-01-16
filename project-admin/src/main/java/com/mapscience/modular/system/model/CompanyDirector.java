package com.mapscience.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 公司权力机构信息表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@TableName("t_company_director")
public class CompanyDirector extends Model<CompanyDirector> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId("company_directort_id")
    private String companyDirectortId;
    /**
     * 公司Id
     */
    @TableField("company_id")
    private String companyId;
    /**
     * 权力机构类型id
     */
    @TableField("company_director_type_child_id")
    private String companyDirectorTypeChildId;
    /**
     * 员工id
     */
    @TableField("employe_id")
    private Integer employeId;
    /**
     * 状态 0是退出 1是在里面 2是其他
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


    public String getCompanyDirectortId() {
        return companyDirectortId;
    }

    public void setCompanyDirectortId(String companyDirectortId) {
        this.companyDirectortId = companyDirectortId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyDirectorTypeChildId() {
        return companyDirectorTypeChildId;
    }

    public void setCompanyDirectorTypeChildId(String companyDirectorTypeChildId) {
        this.companyDirectorTypeChildId = companyDirectorTypeChildId;
    }

    public Integer getEmployeId() {
        return employeId;
    }

    public void setEmployeId(Integer employeId) {
        this.employeId = employeId;
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
        return this.companyDirectortId;
    }

    @Override
    public String toString() {
        return "CompanyDirector{" +
        "companyDirectortId=" + companyDirectortId +
        ", companyId=" + companyId +
        ", companyDirectorTypeChildId=" + companyDirectorTypeChildId +
        ", employeId=" + employeId +
        ", status=" + status +
        ", crateTime=" + crateTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
