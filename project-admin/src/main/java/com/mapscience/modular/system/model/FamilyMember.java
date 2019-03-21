package com.mapscience.modular.system.model;


import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 * 家庭成员表
 * </p>
 *
 * @author ${author}
 * @since 2019-03-14
 */
@ApiModel(value = "家庭成员类")
@TableName("t_family_member")
public class FamilyMember extends Model<FamilyMember> {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    @TableField("id")

    @ApiModelProperty(value="家庭成员id")

    private String id;

    @ApiModelProperty(value="出生年月")
    @TableField("borth_date")
    @Excel(name = "出生日期")
    private Date borthDate;

    /**
     * 称谓
     */
    @TableField("call")
    @Excel(name = "称谓")


    @ApiModelProperty(value="称谓")

    private String call;

    @ApiModelProperty(value="创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value="关联员工id")
    @TableField("emp_id")
    private String empId;

    /**
     * 姓名
     */
    @ApiModelProperty(value="姓名")
    @TableField("name")
    @Excel(name = "姓名")
    private String name;

    @ApiModelProperty(value="政治面貌")
    @TableField("political_look")
    @Excel(name = "政治面貌")
    private String politicalLook;

    @ApiModelProperty(value="修改时间")
    @TableField("update_time")
    private Date updateTime;
    
    @ApiModelProperty(value="工作经验id")
    @TableField("work_unit")
    @Excel(name = "工作单位及职务")
    private String workUnit;

    @ApiModelProperty(value="人员关系表id")
    @TableField("contact_relationship_id")
    private String contactRelationshipId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getBorthDate() {
        return borthDate;
    }

    public void setBorthDate(Date borthDate) {
        this.borthDate = borthDate;
    }

    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoliticalLook() {
        return politicalLook;
    }

    public void setPoliticalLook(String politicalLook) {
        this.politicalLook = politicalLook;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    public String getContactRelationshipId() {
        return contactRelationshipId;
    }

    public void setContactRelationshipId(String contactRelationshipId) {
        this.contactRelationshipId = contactRelationshipId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FamilyMember{" +
        "id=" + id +
        ", borthDate=" + borthDate +
        ", call=" + call +
        ", createTime=" + createTime +
        ", empId=" + empId +
        ", name=" + name +
        ", politicalLook=" + politicalLook +
        ", updateTime=" + updateTime +
        ", workUnit=" + workUnit +
        ", contactRelationshipId=" + contactRelationshipId +
        "}";
    }
}
