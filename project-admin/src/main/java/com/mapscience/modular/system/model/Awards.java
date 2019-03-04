package com.mapscience.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 个人或组织奖惩表
 * </p>
 *
 * @author ${author}
 * @since 2019-02-27
 */
@TableName("t_awards")
public class Awards extends Model<Awards> {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 奖惩组织ID
     */
    private String organization;
    /**
     * 奖惩情况
     */
    private String award;
    /**
     * 奖惩时间
     */
    @TableField("award_time")
    private Date awardTime;
    /**
     * 人员ID
     */
    @TableField("member_id")
    private String memberId;
    /**
     * 类型  0 组织  1 个人
     */
    private Integer categroy;
    /**
     * 1奖励   2 惩处
     */
    private Integer type;
    /**
     * 备注
     */
    private String remark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public Date getAwardTime() {
        return awardTime;
    }

    public void setAwardTime(Date awardTime) {
        this.awardTime = awardTime;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Integer getCategroy() {
        return categroy;
    }

    public void setCategroy(Integer categroy) {
        this.categroy = categroy;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Awards{" +
        "id=" + id +
        ", organization=" + organization +
        ", award=" + award +
        ", awardTime=" + awardTime +
        ", memberId=" + memberId +
        ", categroy=" + categroy +
        ", type=" + type +
        ", remark=" + remark +
        "}";
    }
}
