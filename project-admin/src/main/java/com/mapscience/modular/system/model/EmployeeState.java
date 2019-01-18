package com.mapscience.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 员工状态类型表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@TableName("t_employee_state")
public class EmployeeState extends Model<EmployeeState> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId("employee_state_id")
    private String employeeStateId;
    /**
     * 名称
     */
    @TableField("employee_state_name")
    private String employeeStateName;
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


    public String getEmployeeStateId() {
        return employeeStateId;
    }

    public void setEmployeeStateId(String employeeStateId) {
        this.employeeStateId = employeeStateId;
    }

    public String getEmployeeStateName() {
        return employeeStateName;
    }

    public void setEmployeeStateName(String employeeStateName) {
        this.employeeStateName = employeeStateName;
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
        return this.employeeStateId;
    }

    @Override
    public String toString() {
        return "EmployeeState{" +
        "employeeStateId=" + employeeStateId +
        ", employeeStateName=" + employeeStateName +
        ", status=" + status +
        ", crateTime=" + crateTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
