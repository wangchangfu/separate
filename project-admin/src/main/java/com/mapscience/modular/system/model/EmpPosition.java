package com.mapscience.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 公司部门岗位关系表
 * </p>
 *
 * @author ${author}
 * @since 2019-03-05
 */
@TableName("t_emp_position")
public class EmpPosition extends Model<EmpPosition> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标志
     */
    private String id;
    /**
     * 公司Id
     */
    @TableField("Com_id")
    private String comId;
    /**
     * 部门Id
     */
    @TableField("Dept_id")
    private String deptId;
    /**
     * 岗位Id
     */
    @TableField("Post_id")
    private String postId;
    /**
     * 员工Id
     */
    @TableField("Emp_id")
    private String empId;
    /**
     * 状态:0是删除,1是启用,2是增加,不显示查询显示
     */
    private Integer status;
    /**
     * 创建日期
     */
    @TableField("crate_time")
    private Date crateTime;
    /**
     * 修改日期
     */
    @TableField("update_time")
    private Date updateTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
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
        return this.id;
    }

    @Override
    public String toString() {
        return "EmpPosition{" +
        "id=" + id +
        ", comId=" + comId +
        ", deptId=" + deptId +
        ", postId=" + postId +
        ", empId=" + empId +
        ", status=" + status +
        ", crateTime=" + crateTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
