package com.mapscience.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 岗位类别表
 * </p>
 *
 * @author ${author}
 * @since 2019-03-05
 */
@TableName("t_post_type")
public class PostType extends Model<PostType> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标志
     */
    @TableId("post_type_id")
    private String postTypeId;
    /**
     * 岗位类别名称
     */
    @TableField("post_type_name")
    private String postTypeName;
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


    public String getPostTypeId() {
        return postTypeId;
    }

    public void setPostTypeId(String postTypeId) {
        this.postTypeId = postTypeId;
    }

    public String getPostTypeName() {
        return postTypeName;
    }

    public void setPostTypeName(String postTypeName) {
        this.postTypeName = postTypeName;
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
        return this.postTypeId;
    }

    @Override
    public String toString() {
        return "PostType{" +
        "postTypeId=" + postTypeId +
        ", postTypeName=" + postTypeName +
        ", status=" + status +
        ", crateTime=" + crateTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
