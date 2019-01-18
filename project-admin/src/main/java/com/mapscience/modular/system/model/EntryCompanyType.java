package com.mapscience.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 员工入职公司类型表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@TableName("t_entry_company_type")
public class EntryCompanyType extends Model<EntryCompanyType> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId("entry_company_type_id")
    private String entryCompanyTypeId;
    /**
     * 名称
     */
    @TableField("entry_company_type_name")
    private String entryCompanyTypeName;
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


    public String getEntryCompanyTypeId() {
        return entryCompanyTypeId;
    }

    public void setEntryCompanyTypeId(String entryCompanyTypeId) {
        this.entryCompanyTypeId = entryCompanyTypeId;
    }

    public String getEntryCompanyTypeName() {
        return entryCompanyTypeName;
    }

    public void setEntryCompanyTypeName(String entryCompanyTypeName) {
        this.entryCompanyTypeName = entryCompanyTypeName;
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
        return this.entryCompanyTypeId;
    }

    @Override
    public String toString() {
        return "EntryCompanyType{" +
        "entryCompanyTypeId=" + entryCompanyTypeId +
        ", entryCompanyTypeName=" + entryCompanyTypeName +
        ", status=" + status +
        ", crateTime=" + crateTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
