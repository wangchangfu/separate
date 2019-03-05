package com.mapscience.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 岗位层级表
 * </p>
 *
 * @author ${author}
 * @since 2019-03-05
 */
@TableName("t_post_level")
public class PostLevel extends Model<PostLevel> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标志
     */
    @TableId("post_level_id")
    private String postLevelId;
    /**
     * 岗位层级
     */
    @TableField("post_level_name")
    private String postLevelName;
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


    public String getPostLevelId() {
        return postLevelId;
    }

    public void setPostLevelId(String postLevelId) {
        this.postLevelId = postLevelId;
    }

    public String getPostLevelName() {
        return postLevelName;
    }

    public void setPostLevelName(String postLevelName) {
        this.postLevelName = postLevelName;
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
        return this.postLevelId;
    }

    @Override
    public String toString() {
        return "PostLevel{" +
        "postLevelId=" + postLevelId +
        ", postLevelName=" + postLevelName +
        ", status=" + status +
        ", crateTime=" + crateTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
