package com.mapscience.modular.system.model;

import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 户口类别表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@TableName("t_account_type")
public class AccountType extends Model<AccountType> {

    private static final long serialVersionUID = 1L;

    /**
     * uuid主键
     */
    @TableId("account_type_id")
    @Excel(name = "id")
    private String accountTypeId;
    /**
     * 户口类别名称
     */
    @TableField("account_type_name")
    @Excel(name = "户口性质")
    private String accountTypeName;
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


    public String getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(String accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
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
        return this.accountTypeId;
    }

    @Override
    public String toString() {
        return "AccountType{" +
        "accountTypeId=" + accountTypeId +
        ", accountTypeName=" + accountTypeName +
        ", status=" + status +
        ", crateTime=" + crateTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
