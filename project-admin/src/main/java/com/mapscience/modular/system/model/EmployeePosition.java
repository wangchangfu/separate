package com.mapscience.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 员工职位表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-17
 */
@TableName("t_employee_position")
public class EmployeePosition extends Model<EmployeePosition> {

    private static final long serialVersionUID = 1L;

    /**
     * uuid主键
     */
    @TableId("employee_position_id")
    private String employeePositionId;
    /**
     * 职位id
     */
    @TableField("positon_id")
    private String positonId;
    /**
     * 员工id
     */
    @TableField("employee_id")
    private String employeeId;
    /**
     * 状态,0是删除1是启用2是增加不显示查询显示
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


    public String getEmployeePositionId() {
        return employeePositionId;
    }

    public void setEmployeePositionId(String employeePositionId) {
        this.employeePositionId = employeePositionId;
    }

    public String getPositonId() {
        return positonId;
    }

    public void setPositonId(String positonId) {
        this.positonId = positonId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
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
        return this.employeePositionId;
    }

    @Override
    public String toString() {
        return "EmployeePosition{" +
        "employeePositionId=" + employeePositionId +
        ", positonId=" + positonId +
        ", employeeId=" + employeeId +
        ", status=" + status +
        ", crateTime=" + crateTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
