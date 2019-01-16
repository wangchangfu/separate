package com.mapscience.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 职位表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@TableName("t_position")
public class Position extends Model<Position> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId("position_id")
    private String positionId;
    /**
     * 名称
     */
    @TableField("position_name")
    private String positionName;
    /**
     * 职务id
     */
    @TableField("post_id")
    private String postId;
    /**
     * 公司id
     */
    @TableField("company_id")
    private String companyId;
    /**
     * 状态 0 是禁用 1 是启用
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


    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
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
        return this.positionId;
    }

    @Override
    public String toString() {
        return "Position{" +
        "positionId=" + positionId +
        ", positionName=" + positionName +
        ", postId=" + postId +
        ", companyId=" + companyId +
        ", status=" + status +
        ", crateTime=" + crateTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
