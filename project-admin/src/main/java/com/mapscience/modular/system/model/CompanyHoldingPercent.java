package com.mapscience.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 公司持股比列表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@TableName("t_company_holding_percent")
public class CompanyHoldingPercent extends Model<CompanyHoldingPercent> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId("company_holding_percent_id")
    private String companyHoldingPercentId;
    /**
     * 被持股公司Id
     */
    @TableField("company_id")
    private String companyId;
    /**
     * 持股公司id
     */
    @TableField("holding_company_id")
    private String holdingCompanyId;
    /**
     * 持股用户Id
     */
    @TableField("holding_employee_id")
    private String holdingEmployeeId;
    /**
     * 持股公司名
     */
    @TableField("holding_company_name")
    private String holdingCompanyName;
    /**
     * 持股员工名
     */
    @TableField("holding_employe_name")
    private String holdingEmployeName;
    /**
     * 持股百分比
     */
    private BigDecimal percent;
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


    public String getCompanyHoldingPercentId() {
        return companyHoldingPercentId;
    }

    public void setCompanyHoldingPercentId(String companyHoldingPercentId) {
        this.companyHoldingPercentId = companyHoldingPercentId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getHoldingCompanyId() {
        return holdingCompanyId;
    }

    public void setHoldingCompanyId(String holdingCompanyId) {
        this.holdingCompanyId = holdingCompanyId;
    }

    public String getHoldingEmployeeId() {
        return holdingEmployeeId;
    }

    public void setHoldingEmployeeId(String holdingEmployeeId) {
        this.holdingEmployeeId = holdingEmployeeId;
    }

    public String getHoldingCompanyName() {
        return holdingCompanyName;
    }

    public void setHoldingCompanyName(String holdingCompanyName) {
        this.holdingCompanyName = holdingCompanyName;
    }

    public String getHoldingEmployeName() {
        return holdingEmployeName;
    }

    public void setHoldingEmployeName(String holdingEmployeName) {
        this.holdingEmployeName = holdingEmployeName;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
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
        return this.companyHoldingPercentId;
    }

    @Override
    public String toString() {
        return "CompanyHoldingPercent{" +
        "companyHoldingPercentId=" + companyHoldingPercentId +
        ", companyId=" + companyId +
        ", holdingCompanyId=" + holdingCompanyId +
        ", holdingEmployeeId=" + holdingEmployeeId +
        ", holdingCompanyName=" + holdingCompanyName +
        ", holdingEmployeName=" + holdingEmployeName +
        ", percent=" + percent +
        ", status=" + status +
        ", crateTime=" + crateTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
