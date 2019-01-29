package com.mapscience.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 重要操作日志数据库
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@TableName("t_log")
public class Log extends Model<Log> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId("log_id")
    private String logId;
    /**
     * 那个应用程序进来访问了的(商户ID)
     */
    @TableField("merchant_id")
    private String merchantId;
    /**
     * 日志标题
     */
    private String tiltle;
    /**
     * 日志状态
     */
    private Integer code;
    /**
     * 输入信息字符串
     */
    private String date;
    /**
     * 报错信息
     */
    private String text;
    /**
     * 创建时间
     */
    @TableField("crate_time")
    private Date crateTime;
    /**
     * 请求客户端ip
     */
    private String ip;
    /**
     * 返回信息
     */
    @TableField("return_text")
    private String returnText;


    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getTiltle() {
        return tiltle;
    }

    public void setTiltle(String tiltle) {
        this.tiltle = tiltle;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getReturnText() {
        return returnText;
    }

    public void setReturnText(String returnText) {
        this.returnText = returnText;
    }

    @Override
    protected Serializable pkVal() {
        return this.logId;
    }

    @Override
    public String toString() {
        return "Log{" +
        "logId=" + logId +
        ", merchantId=" + merchantId +
        ", tiltle=" + tiltle +
        ", code=" + code +
        ", date=" + date +
        ", text=" + text +
        ", crateTime=" + crateTime +
        ", ip=" + ip +
        ", returnText=" + returnText +
        "}";
    }
}
