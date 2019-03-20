package com.mapscience.modular.system.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 员工信息表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@ApiModel(value = "员工信息表")
@TableName("t_employee")
public class Employee extends Model<Employee> {

    private static final long serialVersionUID = 1L;

    /**
     * uuid主键
     */
    @ApiModelProperty(value="员工id")
    @TableId("employee_id")
    private String employeeId;
    /**
     * 名称
     */
    @ApiModelProperty(value="员工名称")
    @TableField("employee_name")
    @Excel(name = "姓名")
    private String employeeName;
    /**
     * 证件类型ID
     */

    @ApiModelProperty(value="证件类型ID")
    @TableField("credentials_stype_id")
    private String credentialsStypeId;

    /**
     * 证件号
     */
    @ApiModelProperty(value="证件号ID")
    @TableField("card_id")
    @Excel(name = "身份证号")
    private String cardId;
    /**
     * 性别
     */
    @ApiModelProperty(value="性别")
    @Excel(name = "性别")
    @TableField("gender")
    private String gender;
    /**
     * 出生日期,格式yyyy-MM-dd
     */
    @ApiModelProperty(value="出生日期,格式yyyy-MM-dd")
    @TableField("birth_day")
    @Excel(name = "出生年月(yyyy/m)", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd", isImportField = "true", width = 20)
    private Date birthDay;
    /**
     * 国籍id
     */
    @ApiModelProperty(value="国籍id")
    @TableField("nationality_type_id")
    private String nationalityTypeId;
    /**
     * 民族id
     */
    @ApiModelProperty(value="民族id")
    @TableField("nation_type_id")
    @Excel(name = "民族")
    private String nationTypeId;
    /**
     * 婚姻状况id
     */
    @ApiModelProperty(value="婚姻状况id")
    @TableField("marital_status_id")
    private String maritalStatusId;
    /**
     * 籍贯
     */
    @ApiModelProperty(value="籍贯")
    @TableField("native_place")
    @Excel(name = "籍贯")
    private String nativePlace;
    /**
     * 出生地
     */
    @ApiModelProperty(value="出生地")
    @TableField("birth_place")
    @Excel(name = "出生地")
    private String birthPlace;
    /**
     * 政治面貌Id
     */
    @ApiModelProperty(value="政治面貌Id")
    @TableField("political_status_id")
    @Excel(name = "政治面貌")
    private String politicalStatusId;
    /**
     * 入党日期,yyyy-mm-dd
     */
    @ApiModelProperty(value="入党日期,yyyy-mm-dd")
    @TableField("admission_day")
    @Excel(name = "入党时间(yyyy/m)", databaseFormat = "yyyyMMddHHmmss", format = "yyyy", isImportField = "true", width = 20)
    private Date admissionDay;
    /**
     * 参加工作时间,yyyy-mm
     */
    @ApiModelProperty(value="参加工作时间,yyyy-mm-dd")
    @TableField("join_work_day")
    @Excel(name = "参加工作时间(yyyy/m)", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd", isImportField = "true", width = 20)
    private Date joinWorkDay;
    /**
     * 最高学历
     */
    @ApiModelProperty(value="最高学历")
    @TableField("highest_education")
    private String highestEducation;
    /**
     * 最高学位
     */
    @ApiModelProperty(value="最高学位")
    @TableField("highest_degree")
    private String highestDegree;
    /**
     * 健康状况id
     */
    @ApiModelProperty(value="健康状况id")
    @TableField("health_id")
    @Excel(name = "健康状况")
    private String healthId;
    /**
     * 专业技术职务id
     */
    @ApiModelProperty(value="专业技术职务id")
    @TableField("technical_position_id")
    @Excel(name = "专业技术职务")
    private String technicalPositionId;

    /**
     * 专长
     */
    @ApiModelProperty(value="专长")
    @TableField("zhuanchang")
    @Excel(name="熟悉专业有何专长")
    private String zhuanchang;
    /**
     * 职（执）业资格
     */
    @ApiModelProperty(value="职（执）业资格")
    @TableField("qualification")
    @Excel(name = "职（执）业资格")
    private String qualification;

    /**
     * 最近进入系统时间
     */
    @ApiModelProperty(value="最近进入系统时间")
    @TableField("into_sys_time")
    private Date intoSysTime;

    /**
     * 人员状态id
     */
    @ApiModelProperty(value="人员状态id")
    @TableField("employee_state_id")
    private String employeeStateId;
    /**
     * 进入公司时间
     */
    @ApiModelProperty(value="进入公司时间")
    @TableField("into_company_time")
    private Date intoCompanyTime;
    /**
     * 进入来源Id
     */
    @ApiModelProperty(value="进入来源Id")
    @TableField("source_entry_id")
    private String sourceEntryId;
    /**
     * 户口类别ID
     */
    @ApiModelProperty(value="户口类别ID")
    @TableField("account_type_id")
    private String accountTypeId;
    /**
     * 户口所在地
     */
    @ApiModelProperty(value="户口所在地")
    @TableField("registered_residence")
    private String registeredResidence;
    /**
     * 现居住地
     */
    @ApiModelProperty(value="现居住地")
    @TableField("current_residence")
    private String currentResidence;
    /**
     * 电子邮箱
     */
    @ApiModelProperty(value="电子邮箱")
    @TableField("email")
    private String email;
    /**
     * 办公电话
     */
    @ApiModelProperty(value="办公电话")
    @TableField("phone")
    private String phone;
    /**
     * 手机号
     */
    @ApiModelProperty(value="手机号")
    @TableField("tel")
    private String tel;
    /**
     * 紧急联系人
     */
    @ApiModelProperty(value="紧急联系人")
    @TableField("contact_relationship_name")
    private String contactRelationshipName;
    /**
     * 紧急联系人关系id
     */
    @ApiModelProperty(value="紧急联系人关系id")
    @TableField("contact_relationship_id")
    private String contactRelationshipId;
    /**
     * 紧急联系人电话
     */
    @ApiModelProperty(value="紧急联系人电话")
    @TableField("contact_relationship_phone")
    private String contactRelationshipPhone;
    /**
     * 登录账号
     */
    @TableField("account")
    @ApiModelProperty(value="登录账号")
    private String account;
    /**
     * 密码
     */
    @ApiModelProperty(value="密码")
    @TableField("pass_word")
    private String passWord;
    /**
     * 简拼
     */
    @TableField("jianpin")
    @ApiModelProperty(value="简拼")
    private String jianpin;


    /**
     * 开户行
     */
    @ApiModelProperty(value="开户行")
    @TableField("opening_bank")
    private String openingBank;
    /**
     * 银行账号
     */
    @ApiModelProperty(value="银行账号")
    @TableField("bank_account")
    private String bankAccount;
    /**
     * 类型 1是普通用户 2 超级用户（查看自己公司）3可以查看自己子公司 4 全部 5程序猿的
     */

    @TableField("type")
    @ApiModelProperty(value="类型")
    private Integer type;
    /**
     * 开户行地址
     */
    @ApiModelProperty(value="开户行地址")
    @TableField("opening_bank_adress")
    private String openingBankAdress;
    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    @TableField("crate_time")
    @Excel(name = "填表时间")
    private Date crateTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value="更新时间")
    @TableField("update_time")
    private Date updateTime;

    @Excel(name = "档案所在地")
    @TableField("archives_residence")
    private String archivesResidence;

    @Excel(name = "年度考核结果(非必填项)")
    @TableField("annual_assessment_results")
    private String annualAssessmentResults;


    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", credentialsStypeId='" + credentialsStypeId + '\'' +
                ", cardId='" + cardId + '\'' +
                ", gender='" + gender + '\'' +
                ", birthDay=" + birthDay +
                ", nationalityTypeId='" + nationalityTypeId + '\'' +
                ", nationTypeId='" + nationTypeId + '\'' +
                ", maritalStatusId='" + maritalStatusId + '\'' +
                ", nativePlace='" + nativePlace + '\'' +
                ", birthPlace='" + birthPlace + '\'' +
                ", politicalStatusId='" + politicalStatusId + '\'' +
                ", admissionDay=" + admissionDay +
                ", joinWorkDay=" + joinWorkDay +
                ", highestEducation='" + highestEducation + '\'' +
                ", highestDegree='" + highestDegree + '\'' +
                ", healthId='" + healthId + '\'' +
                ", technicalPositionId='" + technicalPositionId + '\'' +
                ", qualification='" + qualification + '\'' +
                ", intoSysTime=" + intoSysTime +
                ", employeeStateId='" + employeeStateId + '\'' +
                ", intoCompanyTime=" + intoCompanyTime +
                ", sourceEntryId='" + sourceEntryId + '\'' +
                ", accountTypeId='" + accountTypeId + '\'' +
                ", registeredResidence='" + registeredResidence + '\'' +
                ", currentResidence='" + currentResidence + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", tel='" + tel + '\'' +
                ", contactRelationshipName='" + contactRelationshipName + '\'' +
                ", contactRelationshipId='" + contactRelationshipId + '\'' +
                ", contactRelationshipPhone='" + contactRelationshipPhone + '\'' +
                ", account='" + account + '\'' +
                ", passWord='" + passWord + '\'' +
                ", jianpin='" + jianpin + '\'' +
                ", zhuanchang='" + zhuanchang + '\'' +
                ", openingBank='" + openingBank + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", type=" + type +
                ", openingBankAdress='" + openingBankAdress + '\'' +
                ", crateTime=" + crateTime +
                ", updateTime=" + updateTime +
                ", archivesResidence='" + archivesResidence + '\'' +
                ", annualAssessmentResults='" + annualAssessmentResults + '\'' +
                '}';
    }


    public Employee() {
    }


    @Override
    protected Serializable pkVal() {
        return this.employeeId;
    }


    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getCredentialsStypeId() {
        return credentialsStypeId;
    }

    public void setCredentialsStypeId(String credentialsStypeId) {
        this.credentialsStypeId = credentialsStypeId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getNationalityTypeId() {
        return nationalityTypeId;
    }

    public void setNationalityTypeId(String nationalityTypeId) {
        this.nationalityTypeId = nationalityTypeId;
    }

    public String getNationTypeId() {
        return nationTypeId;
    }

    public void setNationTypeId(String nationTypeId) {
        this.nationTypeId = nationTypeId;
    }

    public String getMaritalStatusId() {
        return maritalStatusId;
    }

    public void setMaritalStatusId(String maritalStatusId) {
        this.maritalStatusId = maritalStatusId;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getPoliticalStatusId() {
        return politicalStatusId;
    }

    public void setPoliticalStatusId(String politicalStatusId) {
        this.politicalStatusId = politicalStatusId;
    }

    public Date getAdmissionDay() {
        return admissionDay;
    }

    public void setAdmissionDay(Date admissionDay) {
        this.admissionDay = admissionDay;
    }

    public Date getJoinWorkDay() {
        return joinWorkDay;
    }

    public void setJoinWorkDay(Date joinWorkDay) {
        this.joinWorkDay = joinWorkDay;
    }

    public String getHighestEducation() {
        return highestEducation;
    }

    public void setHighestEducation(String highestEducation) {
        this.highestEducation = highestEducation;
    }

    public String getHighestDegree() {
        return highestDegree;
    }

    public void setHighestDegree(String highestDegree) {
        this.highestDegree = highestDegree;
    }

    public String getHealthId() {
        return healthId;
    }

    public void setHealthId(String healthId) {
        this.healthId = healthId;
    }

    public String getTechnicalPositionId() {
        return technicalPositionId;
    }

    public void setTechnicalPositionId(String technicalPositionId) {
        this.technicalPositionId = technicalPositionId;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Date getIntoSysTime() {
        return intoSysTime;
    }

    public void setIntoSysTime(Date intoSysTime) {
        this.intoSysTime = intoSysTime;
    }

    public String getEmployeeStateId() {
        return employeeStateId;
    }

    public void setEmployeeStateId(String employeeStateId) {
        this.employeeStateId = employeeStateId;
    }

    public Date getIntoCompanyTime() {
        return intoCompanyTime;
    }

    public void setIntoCompanyTime(Date intoCompanyTime) {
        this.intoCompanyTime = intoCompanyTime;
    }

    public String getSourceEntryId() {
        return sourceEntryId;
    }

    public void setSourceEntryId(String sourceEntryId) {
        this.sourceEntryId = sourceEntryId;
    }

    public String getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(String accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getRegisteredResidence() {
        return registeredResidence;
    }

    public void setRegisteredResidence(String registeredResidence) {
        this.registeredResidence = registeredResidence;
    }

    public String getCurrentResidence() {
        return currentResidence;
    }

    public void setCurrentResidence(String currentResidence) {
        this.currentResidence = currentResidence;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getContactRelationshipName() {
        return contactRelationshipName;
    }

    public void setContactRelationshipName(String contactRelationshipName) {
        this.contactRelationshipName = contactRelationshipName;
    }

    public String getContactRelationshipId() {
        return contactRelationshipId;
    }

    public void setContactRelationshipId(String contactRelationshipId) {
        this.contactRelationshipId = contactRelationshipId;
    }

    public String getContactRelationshipPhone() {
        return contactRelationshipPhone;
    }

    public void setContactRelationshipPhone(String contactRelationshipPhone) {
        this.contactRelationshipPhone = contactRelationshipPhone;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getJianpin() {
        return jianpin;
    }

    public void setJianpin(String jianpin) {
        this.jianpin = jianpin;
    }

    public String getZhuanchang() {
        return zhuanchang;
    }

    public void setZhuanchang(String zhuanchang) {
        this.zhuanchang = zhuanchang;
    }

    public String getOpeningBank() {
        return openingBank;
    }

    public void setOpeningBank(String openingBank) {
        this.openingBank = openingBank;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOpeningBankAdress() {
        return openingBankAdress;
    }

    public void setOpeningBankAdress(String openingBankAdress) {
        this.openingBankAdress = openingBankAdress;
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

    public String getArchivesResidence() {
        return archivesResidence;
    }

    public void setArchivesResidence(String archivesResidence) {
        this.archivesResidence = archivesResidence;
    }

    public String getAnnualAssessmentResults() {
        return annualAssessmentResults;
    }

    public void setAnnualAssessmentResults(String annualAssessmentResults) {
        this.annualAssessmentResults = annualAssessmentResults;
    }
}
