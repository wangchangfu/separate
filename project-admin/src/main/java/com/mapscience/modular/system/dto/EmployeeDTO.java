package com.mapscience.modular.system.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.mapscience.modular.system.model.Employee;

public class EmployeeDTO extends Employee {
    private static final long serialVersionUID = -4965143920295563042L;

    @Excel(name = "公司名称")
    private String orgName;

    @Excel(name = "岗位层级")
    private String postLevel;

    @Excel(name = "岗位类别")
    private String postType;

    @Excel(name = "现任职务")
    private String currentPosition;

    @Excel(name = "奖惩情况")
    private String awards;

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
}
