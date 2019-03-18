package com.mapscience.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 员工证照表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@ApiModel(value = "员工证照类")
@TableName("t_certificate")
public class Certificate extends Model<Certificate> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="员工证照id")
    @TableId("certificate_id")
    private String certificateId;

    @ApiModelProperty(value="员工id")
    @TableField("employee_id")
    private String employeeId;

    @ApiModelProperty(value="证书/证件类型")
    @TableField("cer_type")
    private String cerType;

    @ApiModelProperty(value="证书/证件名称")
    @TableField("cer_name")
    private String cerName;

    @ApiModelProperty(value="发证机构")
    @TableField("issuing_agency")
    private String issuingAgency;

    @ApiModelProperty(value="发证日期")
    @TableField("issuing_date")
    private Date issuingDate;

    @ApiModelProperty(value="有效期")
    @TableField("expiry_date")
    private Date expiryDate;

    @ApiModelProperty(value="备注")
    private String remark;

    @ApiModelProperty(value="证书图片")
    @TableField("cer_image")
    private String cerImage;

    @ApiModelProperty(value="保存时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value="更新时间")
    @TableField("update_time")
    private Date updateTime;

    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCerType() {
        return cerType;
    }

    public void setCerType(String cerType) {
        this.cerType = cerType;
    }

    public String getCerName() {
        return cerName;
    }

    public void setCerName(String cerName) {
        this.cerName = cerName;
    }

    public String getIssuingAgency() {
        return issuingAgency;
    }

    public void setIssuingAgency(String issuingAgency) {
        this.issuingAgency = issuingAgency;
    }

    public Date getIssuingDate() {
        return issuingDate;
    }

    public void setIssuingDate(Date issuingDate) {
        this.issuingDate = issuingDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCerImage() {
        return cerImage;
    }

    public void setCerImage(String cerImage) {
        this.cerImage = cerImage;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.certificateId;
    }

    @Override
    public String toString() {
        return "Certificate{" +
        "certificateId=" + certificateId +
        ", employeeId=" + employeeId +
        ", cerType=" + cerType +
        ", cerName=" + cerName +
        ", issuingAgency=" + issuingAgency +
        ", issuingDate=" + issuingDate +
        ", expiryDate=" + expiryDate +
        ", remark=" + remark +
        ", cerImage=" + cerImage +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
