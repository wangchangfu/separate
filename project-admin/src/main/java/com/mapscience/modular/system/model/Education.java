package com.mapscience.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 教育信息表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@ApiModel(value = "教育信息类")
@TableName("t_education")
public class Education extends Model<Education> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="教育信息id")
    @TableId("education_id")
    private String educationId;
    
    @ApiModelProperty(value="员工id")
    @TableField("employee_id")
    private String employeeId;
    
    @ApiModelProperty(value="学历类型id")
    @TableField("education_type_id")
    private String educationTypeId;
    
    @ApiModelProperty(value="学位类型id")
    @TableField("degree_type_id")
    private String degreeTypeId;
    
    @ApiModelProperty(value="学位名称")
    @TableField("degree_name")
    private String degreeName;
    
    @ApiModelProperty(value="入学时间")
    @TableField("admission_time")
    private Date admissionTime;
   
    @ApiModelProperty(value="学位授予日期/毕业时间")
    @TableField("degree_date")
    private Date degreeDate;
    
    @ApiModelProperty(value="毕业专业")
    private String major;
   
    @ApiModelProperty(value="学位授予单位")
    @TableField("degree_unit")
    private String degreeUnit;
    
    @ApiModelProperty(value="学历证书编号")
    @TableField("educationctif_code")
    private String educationctifCode;
    
    @ApiModelProperty(value="学位证书编号")
    @TableField("certif_code")
    private String certifCode;
    
    @ApiModelProperty(value="教育类型id")
    @TableField("record_type_id")
    private String recordTypeId;
   
    @ApiModelProperty(value="是否最高学历")
    @TableField("is_highest_degree")
    private Integer isHighestDegree;
  
    @ApiModelProperty(value="备注")
    private String remark;
  
    @ApiModelProperty(value="保存时间")
    @TableField("create_time")
    private Date createTime;
    
    @ApiModelProperty(value="修改时间")
    @TableField("update_time")
    private Date updateTime;

    public String getEducationId() {
        return educationId;
    }

    public void setEducationId(String educationId) {
        this.educationId = educationId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEducationTypeId() {
        return educationTypeId;
    }

    public void setEducationTypeId(String educationTypeId) {
        this.educationTypeId = educationTypeId;
    }

    public String getDegreeTypeId() {
        return degreeTypeId;
    }

    public void setDegreeTypeId(String degreeTypeId) {
        this.degreeTypeId = degreeTypeId;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public Date getAdmissionTime() {
        return admissionTime;
    }

    public void setAdmissionTime(Date admissionTime) {
        this.admissionTime = admissionTime;
    }

    public Date getDegreeDate() {
        return degreeDate;
    }

    public void setDegreeDate(Date degreeDate) {
        this.degreeDate = degreeDate;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDegreeUnit() {
        return degreeUnit;
    }

    public void setDegreeUnit(String degreeUnit) {
        this.degreeUnit = degreeUnit;
    }

    public String getEducationctifCode() {
        return educationctifCode;
    }

    public void setEducationctifCode(String educationctifCode) {
        this.educationctifCode = educationctifCode;
    }

    public String getCertifCode() {
        return certifCode;
    }

    public void setCertifCode(String certifCode) {
        this.certifCode = certifCode;
    }

    public String getRecordTypeId() {
        return recordTypeId;
    }

    public void setRecordTypeId(String recordTypeId) {
        this.recordTypeId = recordTypeId;
    }


    public Integer getIsHighestDegree() {
        return isHighestDegree;
    }

    public void setIsHighestDegree(Integer isHighestDegree) {
        this.isHighestDegree = isHighestDegree;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        return this.educationId;
    }

    @Override
    public String toString() {
        return "Education{" +
        "educationId=" + educationId +
        ", employeeId=" + employeeId +
        ", educationTypeId=" + educationTypeId +
        ", degreeTypeId=" + degreeTypeId +
        ", degreeName=" + degreeName +
        ", admissionTime=" + admissionTime +
        ", degreeDate=" + degreeDate +
        ", major=" + major +
        ", degreeUnit=" + degreeUnit +
        ", educationctifCode=" + educationctifCode +
        ", certifCode=" + certifCode +
        ", recordTypeId=" + recordTypeId +
        ", isHighestDegree=" + isHighestDegree +
        ", remark=" + remark +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
