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
 * 公司基本信息表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@TableName("t_company")
public class Company extends Model<Company> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId("company_id")
    private String companyId;
    /**
     * 公司名
     */
    @TableField("company_name")
    private String companyName;
    /**
     * 父id
     */
    @TableField("parent_id")
    private String parentId;
    /**
     * 法定代表人
     */
    @TableField("legal_representative")
    private String legalRepresentative;
    /**
     * 注册资本（万元）
     */
    @TableField("registered_capital")
    private BigDecimal registeredCapital;
    /**
     * 公司坐标（格式123,123|经度,纬度）
     */
    private String coordinate;
    /**
     * 公司地址
     */
    private String address;
    /**
     * 官网地址
     */
    @TableField("website_address")
    private String websiteAddress;
    /**
     * 公司图标地址
     */
    @TableField("company_logo")
    private String companyLogo;
    /**
     * 公司电话（可以多个如023-44951826,0236-954217451）
     */
    @TableField("company_phone")
    private String companyPhone;
    /**
     * 业务范围
     */
    @TableField("company_buss_range")
    private String companyBussRange;
    /**
     * 公司发票号
     */
    @TableField("company_card")
    private String companyCard;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 描述
     */
    private String remark;
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


    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public BigDecimal getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(BigDecimal registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsiteAddress() {
        return websiteAddress;
    }

    public void setWebsiteAddress(String websiteAddress) {
        this.websiteAddress = websiteAddress;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyBussRange() {
        return companyBussRange;
    }

    public void setCompanyBussRange(String companyBussRange) {
        this.companyBussRange = companyBussRange;
    }

    public String getCompanyCard() {
        return companyCard;
    }

    public void setCompanyCard(String companyCard) {
        this.companyCard = companyCard;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        return this.companyId;
    }

    @Override
    public String toString() {
        return "Company{" +
        "companyId=" + companyId +
        ", companyName=" + companyName +
        ", parentId=" + parentId +
        ", legalRepresentative=" + legalRepresentative +
        ", registeredCapital=" + registeredCapital +
        ", coordinate=" + coordinate +
        ", address=" + address +
        ", websiteAddress=" + websiteAddress +
        ", companyLogo=" + companyLogo +
        ", companyPhone=" + companyPhone +
        ", companyBussRange=" + companyBussRange +
        ", companyCard=" + companyCard +
        ", status=" + status +
        ", remark=" + remark +
        ", crateTime=" + crateTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
