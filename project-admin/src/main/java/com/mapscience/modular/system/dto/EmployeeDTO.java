package com.mapscience.modular.system.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.mapscience.modular.system.model.Employee;

public class EmployeeDTO extends Employee {

    private static final long serialVersionUID = -4965143920295563042L;

    @TableField("company_name")
    @Excel(name = "公司名称")
    private String companyName;

    @Excel(name = "从业人员岗位层级")
    @TableField("post_level_name")
    private String postLevel;

    @Excel(name = "从业人员岗位类别")
    @TableField("post_type_name")
    private String postType;

    @Excel(name = "现任职务（岗位）")
    @TableField("post_nam")
    private String currentPosition;

    @TableField("award")
    @Excel(name = "奖惩情况")
    private String awards;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPostLevel() {
        return postLevel;
    }

    public void setPostLevel(String postLevel) {
        this.postLevel = postLevel;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "companyName='" + companyName + '\'' +
                ", postLevel='" + postLevel + '\'' +
                ", postType='" + postType + '\'' +
                ", currentPosition='" + currentPosition + '\'' +
                ", awards='" + awards + '\'' +
                '}';
    }
}
