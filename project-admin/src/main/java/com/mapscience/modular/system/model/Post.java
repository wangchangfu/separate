package com.mapscience.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 岗位表
 * </p>
 *
 * @author ${author}
 * @since 2019-03-05
 */
@TableName("t_post")
public class Post extends Model<Post> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标志
     */
    @TableId("post_id")
    private String postId;
    /**
     * 名称
     */
    @TableField("post_name")
    private String postName;
    /**
     * 状态 0 禁用1启用
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
    /**
     * 岗位层级ID
     */
    @TableField("post_level_id")
    private String postLevelId;
    /**
     * 岗位类别id
     */
    @TableField("post_type_id")
    private String postTypeId;



    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
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

    public String getPostLevelId() {
        return postLevelId;
    }

    public void setPostLevelId(String postLevelId) {
        this.postLevelId = postLevelId;
    }

    public String getPostTypeId() {
        return postTypeId;
    }

    public void setPostTypeId(String postTypeId) {
        this.postTypeId = postTypeId;
    }



    @Override
    protected Serializable pkVal() {
        return this.postId;
    }

    @Override
    public String toString() {
        return "Post{" +
        "postId=" + postId +
        ", postName=" + postName +
        ", status=" + status +
        ", crateTime=" + crateTime +
        ", updateTime=" + updateTime +
        ", postLevelId=" + postLevelId +
        ", postTypeId=" + postTypeId +
        "}";
    }
}
