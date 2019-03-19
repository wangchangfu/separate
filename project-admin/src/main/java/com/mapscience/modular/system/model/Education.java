package com.mapscience.modular.system.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javafx.fxml.FXML;

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
@TableName("t_education")
public class Education extends Model<Education> {

    private static final long serialVersionUID = 1L;

    /**
     * uuid
     */
    @TableId("education_id")
    private String educationId;
    /**
     * 员工id
     */
    @TableField("employee_id")
    private String employeeId;

    /**
     * 学历类型ID
     */
    @TableField("education_type_id")
    @Excel(name = "全日制学历")
    private String educationTypeId;

    /**
     * 学位类型ID
     */
    @TableField("degree_type_id")
    @Excel(name = "全日制学位")
    private String degreeTypeId;
    /**
     * 学位名称
     */
    @TableField("degree_name")
    private String degreeName;
    /**
     * 入学时间
     */
    @TableField("admission_time")
    private Date admissionTime;

    /**
     * 学位授予日期/毕业时间
     */
    @TableField("degree_date")
    private Date degreeDate;

    /**
     * 毕业专业
     */
    @TableField("major")
    @Excel(name = "全日制专业")
    private String major;
    /**
     * 学位授予单位
     */
    @TableField("degree_unit")
    @Excel(name = "全日制毕业院校")
    private String degreeUnit;
    /**
     * 学历证书编号
     */
    @TableField("educationctif_code")
    private String educationctifCode;
    /**
     * 学位证书编号
     */
    @TableField("certif_code")
    private String certifCode;
    /**
     * 教育类型ID
     */
    @TableField("record_type_id")
    private String recordTypeId;
    /**
     * 是否最高学历
     */
    @TableField("is_highest_degree")
    private Integer isHighestDegree;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
    /**
     * 保存时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;



    public String getDegreeUnit() {
        return degreeUnit;
    }

    public void setDegreeUnit(String degreeUnit) {
        this.degreeUnit = degreeUnit;
    }



    @Override
    protected Serializable pkVal() {
        return this.educationId;
    }

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

    public String getFullTimeDegreeUnit() {
        return degreeUnit;
    }

    public void setFullTimeDegreeUnit(String fullTimeDegreeUnit) {
        this.degreeUnit = fullTimeDegreeUnit;
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
    public String toString() {
        return "Education{" +
                "educationId='" + educationId + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", educationTypeId='" + educationTypeId + '\'' +
                ", degreeTypeId='" + degreeTypeId + '\'' +
                ", degreeName='" + degreeName + '\'' +
                ", admissionTime=" + admissionTime +
                ", degreeDate=" + degreeDate +
                ", major='" + major + '\'' +
                ", fullTimeDegreeUnit='" + degreeUnit + '\'' +
                ", educationctifCode='" + educationctifCode + '\'' +
                ", certifCode='" + certifCode + '\'' +
                ", recordTypeId='" + recordTypeId + '\'' +
                ", isHighestDegree=" + isHighestDegree +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
