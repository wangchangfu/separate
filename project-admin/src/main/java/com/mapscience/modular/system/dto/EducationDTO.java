package com.mapscience.modular.system.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.mapscience.modular.system.model.Education;

public class EducationDTO extends Education {

    @TableField("employee_name")
    @Excel(name = "员工本人姓名")
    private  String employeeName;

    @TableField("card_id")
    @Excel(name = "本人身份证号")
    private String cardId;

    /**
     * 学历类型ID
     */
    //@TableField("education_type_id")
    @Excel(name = "在职学历")
    private String workEducationTypeId;
    /**
     * 学位类型ID
     */
    //@TableField("degree_type_id")
    @Excel(name = "在职学位")
    private String workDegreeTypeId;

    /**
     * 毕业专业
     */
    //@TableField("major")
    @Excel(name = "在职专业")
    private String workMajor;
    /**
     * 学位授予单位
     */
    //@TableField("degree_unit")
    @Excel(name = "在职毕业院校")
    private String workDegreeUnit;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getWorkEducationTypeId() {
        return workEducationTypeId;
    }

    public void setWorkEducationTypeId(String workEducationTypeId) {
        this.workEducationTypeId = workEducationTypeId;
    }

    public String getWorkDegreeTypeId() {
        return workDegreeTypeId;
    }

    public void setWorkDegreeTypeId(String workDegreeTypeId) {
        this.workDegreeTypeId = workDegreeTypeId;
    }

    public String getWorkMajor() {
        return workMajor;
    }

    public void setWorkMajor(String workMajor) {
        this.workMajor = workMajor;
    }

    public String getWorkDegreeUnit() {
        return workDegreeUnit;
    }

    public void setWorkDegreeUnit(String workDegreeUnit) {
        this.workDegreeUnit = workDegreeUnit;
    }

    @Override
    public String toString() {
        return "EducationDTO{" +
                "employeeName='" + employeeName + '\'' +
                ", cardId='" + cardId + '\'' +
                ", workEducationTypeId='" + workEducationTypeId + '\'' +
                ", workDegreeTypeId='" + workDegreeTypeId + '\'' +
                ", workMajor='" + workMajor + '\'' +
                ", workDegreeUnit='" + workDegreeUnit + '\'' +
                '}';
    }
}
