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
 * 股东出资表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@TableName("t_shareholder_contribution")
public class ShareholderContribution extends Model<ShareholderContribution> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId("shareholder_contribution_id")
    private String shareholderContributionId;
    /**
     * 被出资的公司Id
     */
    @TableField("company_id")
    private String companyId;
    /**
     * 股东名称
     */
    @TableField("shareholder_name")
    private String shareholderName;
    /**
     * 员工id
     */
    @TableField("shareholder_id")
    private String shareholderId;
    /**
     * 股东出资金额（万元）
     */
    @TableField("shareholder_contribution")
    private BigDecimal shareholderContribution;
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


    public String getShareholderContributionId() {
        return shareholderContributionId;
    }

    public void setShareholderContributionId(String shareholderContributionId) {
        this.shareholderContributionId = shareholderContributionId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getShareholderName() {
        return shareholderName;
    }

    public void setShareholderName(String shareholderName) {
        this.shareholderName = shareholderName;
    }

    public String getShareholderId() {
        return shareholderId;
    }

    public void setShareholderId(String shareholderId) {
        this.shareholderId = shareholderId;
    }

    public BigDecimal getShareholderContribution() {
        return shareholderContribution;
    }

    public void setShareholderContribution(BigDecimal shareholderContribution) {
        this.shareholderContribution = shareholderContribution;
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
        return this.shareholderContributionId;
    }

    @Override
    public String toString() {
        return "ShareholderContribution{" +
        "shareholderContributionId=" + shareholderContributionId +
        ", companyId=" + companyId +
        ", shareholderName=" + shareholderName +
        ", shareholderId=" + shareholderId +
        ", shareholderContribution=" + shareholderContribution +
        ", status=" + status +
        ", crateTime=" + crateTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
