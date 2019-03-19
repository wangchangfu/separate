package com.mapscience.modular.system.model;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@ApiModel(value = "部门实体类")
@TableName("t_department")
public class Department extends Model<Department> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="部门id")
    @TableId("department_id")
    private String departmentId;
    
    @ApiModelProperty(value="公司id")
    @TableField("company_id")
    private String companyId;
    
    @ApiModelProperty(value="部门名称")
    @TableField("department_name")
    private String departmentName;
   
    @ApiModelProperty(value="父id")
    @TableField("parent_id")
    private String parentId;
    
    @ApiModelProperty(value="状态 0禁用 1启用")
    private Integer status;
    
    @ApiModelProperty(value="创建时间")
    @TableField("crate_time")
    private Date crateTime;
   
    @ApiModelProperty(value="更新时间")
    @TableField("update_time")
    private Date updateTime;
    
    @ApiModelProperty(value="子部门或其他")
    @TableField(exist = false)
    public List<Object> objectChildren;
    
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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
        return this.departmentId;
    }
    
	public List<Object> getObjectChildren() {
		return objectChildren;
	}

	public void setObjectChildren(List<Object> objectChildren) {
		this.objectChildren = objectChildren;
	}

	@Override
    public String toString() {
        return "Department{" +
        "departmentId=" + departmentId +
        ", companyId=" + companyId +
        ", departmentName=" + departmentName +
        ", parentId=" + parentId +
        ", status=" + status +
        ", crateTime=" + crateTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
