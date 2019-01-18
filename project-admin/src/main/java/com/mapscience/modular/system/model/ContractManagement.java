package com.mapscience.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 合同表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@TableName("t_contract_management")
public class ContractManagement extends Model<ContractManagement> {

    private static final long serialVersionUID = 1L;

    /**
     * uuid
     */
    @TableId("contract_management_id")
    private String contractManagementId;
    /**
     * 员工id
     */
    @TableField("employee_id")
    private String employeeId;
    /**
     * 工作性质类型id
     */
    @TableField("employee_state_id")
    private String employeeStateId;
    /**
     * 合同编号
     */
    @TableField("contract_num")
    private String contractNum;
    /**
     * 签约公司（公司ID）
     */
    @TableField("signing_com_id")
    private String signingComId;
    /**
     * 合同类型
     */
    @TableField("contract_type")
    private String contractType;
    /**
     * 合同状态
     */
    private Integer status;
    /**
     * 合同年限
     */
    private Integer term;
    /**
     * 合同文件
     */
    @TableField("contract_file")
    private String contractFile;
    /**
     * 开始时间
     */
    @TableField("start_time")
    private Date startTime;
    /**
     * 结束时间
     */
    @TableField("end_time")
    private Date endTime;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;


    public String getContractManagementId() {
        return contractManagementId;
    }

    public void setContractManagementId(String contractManagementId) {
        this.contractManagementId = contractManagementId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeStateId() {
        return employeeStateId;
    }

    public void setEmployeeStateId(String employeeStateId) {
        this.employeeStateId = employeeStateId;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public String getSigningComId() {
        return signingComId;
    }

    public void setSigningComId(String signingComId) {
        this.signingComId = signingComId;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public String getContractFile() {
        return contractFile;
    }

    public void setContractFile(String contractFile) {
        this.contractFile = contractFile;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
        return this.contractManagementId;
    }

    @Override
    public String toString() {
        return "ContractManagement{" +
        "contractManagementId=" + contractManagementId +
        ", employeeId=" + employeeId +
        ", employeeStateId=" + employeeStateId +
        ", contractNum=" + contractNum +
        ", signingComId=" + signingComId +
        ", contractType=" + contractType +
        ", status=" + status +
        ", term=" + term +
        ", contractFile=" + contractFile +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
