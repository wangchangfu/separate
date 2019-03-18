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
 * 工作经验表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@ApiModel(value = "工作经验类")
@TableName("t_work_history")
public class WorkHistory extends Model<WorkHistory> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="工作经验id")
    @TableId("work_history_id")
    private String workHistoryId;

    @ApiModelProperty(value="公司名称")
    @TableField("work_history_name")
    private String workHistoryName;

    @ApiModelProperty(value="员工id")
    @TableField("employee_id")
    private String employeeId;

    @ApiModelProperty(value="担任职位")
    private String post;

    @ApiModelProperty(value="职位描述")
    @TableField("post_remark")
    private String postRemark;

    @ApiModelProperty(value="证明人")
    private String witness;

    @ApiModelProperty(value="证明人电话")
    private String tel;

    @ApiModelProperty(value="入职时间")
    @TableField("start_time")
    private Date startTime;

    @ApiModelProperty(value="离职时间")
    @TableField("leave_time")
    private Date leaveTime;

    @ApiModelProperty(value="创建时间")
    @TableField("create_time")
    private Date createTime;
    
    @ApiModelProperty(value="修改时间")
    @TableField("update_time")
    private Date updateTime;

    public String getWorkHistoryId() {
        return workHistoryId;
    }

    public void setWorkHistoryId(String workHistoryId) {
        this.workHistoryId = workHistoryId;
    }

    public String getWorkHistoryName() {
        return workHistoryName;
    }

    public void setWorkHistoryName(String workHistoryName) {
        this.workHistoryName = workHistoryName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPostRemark() {
        return postRemark;
    }

    public void setPostRemark(String postRemark) {
        this.postRemark = postRemark;
    }

    public String getWitness() {
        return witness;
    }

    public void setWitness(String witness) {
        this.witness = witness;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
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
        return this.workHistoryId;
    }

    @Override
    public String toString() {
        return "WorkHistory{" +
        "workHistoryId=" + workHistoryId +
        ", workHistoryName=" + workHistoryName +
        ", employeeId=" + employeeId +
        ", post=" + post +
        ", postRemark=" + postRemark +
        ", witness=" + witness +
        ", tel=" + tel +
        ", startTime=" + startTime +
        ", leaveTime=" + leaveTime +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
