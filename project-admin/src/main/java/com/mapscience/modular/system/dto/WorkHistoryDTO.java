package com.mapscience.modular.system.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.mapscience.modular.system.model.WorkHistory;

import java.io.Serializable;

public class WorkHistoryDTO extends WorkHistory implements Serializable {


    @TableField("employee_name")
    @Excel(name = "员工本人姓名")
    private String employeeName;

    @TableField("card_id")
    @Excel(name = "本人身份证号码")
    private String cardId;


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


    @Override
    public String toString() {
        return "WorkHistoryDTO{" +
                "employeeName='" + employeeName + '\'' +
                ", cardId='" + cardId + '\'' +
                '}';
    }
}
