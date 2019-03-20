package com.mapscience.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 公司基本信息表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@ApiModel(value = "公司实体类")
@TableName("t_company")
public class Company extends Model<Company> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="公司id")
    @Excel(name = "公司id")
    @TableId("company_id")
    private String companyId;
    
    @ApiModelProperty(value="公司名")
    @Excel(name = "公司名")
    @TableField("company_name")
    private String companyName;
    
    @ApiModelProperty(value="父id")
    @Excel(name = "父id")
    @TableField("parent_id")
    private String parentId;
    
    @ApiModelProperty(value="法定代表人")
    @TableField("legal_representative")
    private String legalRepresentative;
    
    @ApiModelProperty(value="注册资本（万元）")
    @TableField("registered_capital")
    private BigDecimal registeredCapital;
    
    @ApiModelProperty(value="纬度")
    @Excel(name = "纬度")
    @TableField("latitude")
    private String latitude;
    
    @ApiModelProperty(value="经度")
    @Excel(name = "经度")
    @TableField("longitude")
    private String longitude;
   
    @ApiModelProperty(value="公司地址")
    @Excel(name = "公司地址")
    @TableField("address")
    private String address;
   
    @ApiModelProperty(value="官网地址")
    @Excel(name = "官网地址")
    @TableField("website_address")
    private String websiteAddress;

    @ApiModelProperty(value="公司图标地址")
    @Excel(name = "公司图标地址")
    @TableField("company_logo")
    private String companyLogo;
    
    @ApiModelProperty(value="公司电话（可以多个如023-44951826,0236-954217451）")
    @Excel(name = "公司电话")
    @TableField("company_phone")
    private String companyPhone;

    @ApiModelProperty(value="业务范围")
    @TableField("company_buss_range")
    private String companyBussRange;

    @ApiModelProperty(value="公司发票号")
    @TableField("company_card")
    private String companyCard;

    @ApiModelProperty(value="状态")
    @Excel(name = "状态")
    @TableField("status")
    private Integer status;
    
    @ApiModelProperty(value="描述")
    @TableField("remark")
    private String remark;
    
    @ApiModelProperty(value="创建时间")
    @TableField("crate_time")
    private Date crateTime;
    
    @ApiModelProperty(value="更新时间")
    @TableField("update_time")
    private Date updateTime;
    
    @ApiModelProperty(value="子公司")
    @TableField(exist = false)
    public List<Company> children;
    
    @ApiModelProperty(value="部门或子公司或其他")
    @TableField(exist = false)
    public List<Object> objectChildren;
    
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

    public List<Company> getChildren() {
		return children;
	}

	public void setChildren(List<Company> children) {
		this.children = children;
	}

	public List<Object> getObjectChildren() {
		return objectChildren;
	}

	public void setObjectChildren(List<Object> objectChildren) {
		this.objectChildren = objectChildren;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Override
    protected Serializable pkVal() {
        return this.companyId;
    }

	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", companyName=" + companyName + ", parentId=" + parentId
				+ ", legalRepresentative=" + legalRepresentative + ", registeredCapital=" + registeredCapital
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", address=" + address + ", websiteAddress="
				+ websiteAddress + ", companyLogo=" + companyLogo + ", companyPhone=" + companyPhone
				+ ", companyBussRange=" + companyBussRange + ", companyCard=" + companyCard + ", status=" + status
				+ ", remark=" + remark + ", crateTime=" + crateTime + ", updateTime=" + updateTime + ", children="
				+ children + ", objectChildren=" + objectChildren + "]";
	}
	
}
