package com.mapscience.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 商户表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@TableName("t_merchant")
public class Merchant extends Model<Merchant> {

    private static final long serialVersionUID = 1L;

    /**
     * uuid主键
     */
    @TableId("merchant_id")
    private Integer merchantId;
    /**
     * 商户名称
     */
    @TableField("merchant_name")
    private String merchantName;
    /**
     * 商户密钥
     */
    @TableField("merchant_key")
    private String merchantKey;
    /**
     * 商户状态 1是可以访问  0 是禁止访问
     */
    private Integer status;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;


    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantKey() {
        return merchantKey;
    }

    public void setMerchantKey(String merchantKey) {
        this.merchantKey = merchantKey;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        return this.merchantId;
    }

    @Override
    public String toString() {
        return "Merchant{" +
        "merchantId=" + merchantId +
        ", merchantName=" + merchantName +
        ", merchantKey=" + merchantKey +
        ", status=" + status +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
