package com.mapscience.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 联系人关系表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@TableName("t_contact_relationship")
public class ContactRelationship extends Model<ContactRelationship> {

    private static final long serialVersionUID = 1L;

    /**
     * uuid主键
     */
    @TableId("contact_relationship_id")
    private String contactRelationshipId;
    /**
     * 联系人关系名称
     */
    @TableField("contact_relationship_name")
    private String contactRelationshipName;
    /**
     * 0是删除1是启用2是增加不显示查询显示
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


    public String getContactRelationshipId() {
        return contactRelationshipId;
    }

    public void setContactRelationshipId(String contactRelationshipId) {
        this.contactRelationshipId = contactRelationshipId;
    }

    public String getContactRelationshipName() {
        return contactRelationshipName;
    }

    public void setContactRelationshipName(String contactRelationshipName) {
        this.contactRelationshipName = contactRelationshipName;
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
        return this.contactRelationshipId;
    }

    @Override
    public String toString() {
        return "ContactRelationship{" +
        "contactRelationshipId=" + contactRelationshipId +
        ", contactRelationshipName=" + contactRelationshipName +
        ", status=" + status +
        ", crateTime=" + crateTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
