package com.mapscience.modular.system.controller.excel;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.util.excel.EasyPOIExcelUtile;
import com.mapscience.modular.system.dto.EducationDTO;
import com.mapscience.modular.system.dto.EmployeeDTO;
import com.mapscience.modular.system.dto.FamilyMemberDTO;
import com.mapscience.modular.system.dto.WorkHistoryDTO;
import com.mapscience.modular.system.model.*;
import com.mapscience.modular.system.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.*;


/**
 * 操作表格
 */
@Api(tags = "表格导入导出")
@Controller
@RequestMapping("/excel")
@Transactional
public class ExcelController {

    /**
     * 人员基本信息
     */
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private IEducationService educationService;

    @Autowired
    private IWorkHistoryService workHistoryService;

    @Autowired
    private IContactRelationshipService contactRelationshipService;

    @Autowired
    private IFamilyMemberService familyMemberService;

    @Autowired
    private IRecordTypeService recordTypeService;

    @Autowired
    private IEducationTypeService educationTypeService;

    @Autowired
    private IDegreeTypeService degreeTypeService;

    @Autowired
    private INationTypeService nationTypeService;

    @Autowired
    private INationalityTypeService nationalityTypeService;

    @Autowired
    private IMaritalStatusService maritalStatusService;

    @Autowired
    private IPoliticalStatusService politicalStatusService;

    @Autowired
    private IHealthService healthService;

    @Autowired
    private ITechnicalPositionService technicalPositionService;

    @Autowired
    private IEmployeeStateService employeeStateService;

    @Autowired
    private ISourceEntryService sourceEntryService;

    @Autowired
    private IAccountTypeService accountTypeService;

    @Autowired
    private IEmpPositionService empPositionService;

    /**
     * 获取导出模板 /数据
     *
     * @param request
     * @param method  0 模板 1 数据
     * @param type    导出模板类型 1 员工信息 2 教育经历 3 证书证件 4 工作经历
     * @param ids     导出员工的ID
     */
    @RequestMapping("/getModelExcel")
    @ResponseBody
    public ResponseVal exportEmploy(HttpServletRequest request, Integer method, Integer type, String ids,
                                    HttpServletResponse response) {
        List<Employee> list = employeeService.getList();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("员工信息表", "员工信息"), Employee.class, list);
        return new ResponseVal(0, "测试", null);
    }

    /**
     * 导出空表格
     *
     * @param response
     * @return
     */
    @RequestMapping("/export")
    @ResponseBody
    public ResponseVal export(HttpServletResponse response) {
        EasyPOIExcelUtile.exportExcel(new ArrayList<EmployeeDTO>(), "员工基本信息", "基本信息", EmployeeDTO.class, "员工基本信息.xls",
                response);
        return new ResponseVal(0, "测试", null);
    }

    /**
     * 导入excel信息
     *
     * @param request
     * @param files   innerText
     * @return
     */
    @ApiOperation(value = "导入excel员工")
    @ResponseBody
    @RequestMapping(value = "/importExcelEmp", method = RequestMethod.POST)
    public ResponseVal importExcelEmp(HttpServletRequest request,
                                      @RequestParam(value = "file", required = false) MultipartFile files) {
        try {
            // 获取workbook
            Workbook workBook = EasyPOIExcelUtile.getWorkBook(files);

            ImportParams params = new ImportParams();
            params.setTitleRows(1);
            params.setHeadRows(1);

            Sheet sh = null;


            // 循环工作表获取sheet
            for (int n = 0; n < workBook.getNumberOfSheets(); n++) {

                params.setStartSheetIndex(n);

                // 循环表获取页
                sh = workBook.getSheetAt(n);
                String sheetName = sh.getSheetName();

                if ("基本信息".equals(sheetName)) {
                    List<EmployeeDTO> list = ExcelImportUtil.importExcel(files.getInputStream(), EmployeeDTO.class, params);
                    for (EmployeeDTO employeeDTO : list) {
                        Employee employee = new Employee();
                        // System.out.println(employeeDTO.getNationalityTypeId());
                        //姓名
                        employee.setEmployeeName(employeeDTO.getEmployeeName());
                        //证件类型ID
                        //employee.setCredentialsStypeId(employeeDTO.getCardId());
                        //证件号
                        employee.setCardId(employeeDTO.getCardId());
                        //性别
                        employee.setGender(employeeDTO.getGender());
                        //出生日期,格式yyyy-MM-dd
                        employee.setBirthDay(employeeDTO.getBirthDay());
                        //国籍id
                        NationalityType nationalityType = nationalityTypeService.getnationalityTypeByName(employeeDTO.getNationalityTypeId());
                        if (nationalityType != null) {
                            employee.setNationalityTypeId(nationalityType.getNationalityStypeId());
                        }
                        //民族id
                        NationType nationTyprByName = nationTypeService.getNationTypeByName(employeeDTO.getNationTypeId());
                        if (nationTyprByName != null) {
                            employee.setNationTypeId(nationTyprByName.getNationStypeId());
                        }
                        //婚姻状况id
                        MaritalStatus maritalStatusByName = maritalStatusService.getMaritalStatusByName(employeeDTO.getMaritalStatusId());
                        if (maritalStatusByName != null) {
                            employee.setMaritalStatusId(maritalStatusByName.getMaritalStatusId());
                        }
                        //籍贯
                        employee.setNativePlace(employeeDTO.getNativePlace());
                        //出生地
                        employee.setBirthPlace(employeeDTO.getBirthPlace());
                        //政治面貌Id
                        PoliticalStatus politicalStatusByName = politicalStatusService.getPoliticalStatusByName(employeeDTO.getPoliticalStatusId());
                        if (politicalStatusByName != null) {
                            employee.setPoliticalStatusId(politicalStatusByName.getPoliticalStatusId());
                        }
                        //入党日期,yyyy-mm-dd
                        employee.setAdmissionDay(employeeDTO.getAdmissionDay());
                        //参加工作时间,yyyy-mm
                        employee.setJoinWorkDay(employeeDTO.getJoinWorkDay());
                        //最高学历
                        employee.setHighestEducation(employeeDTO.getHighestEducation());
                        //最高学位
                        employee.setHighestDegree(employeeDTO.getHighestDegree());
                        //健康状况id
                        Health health = healthService.getHealth(employeeDTO.getHealthId());
                        if (health != null) {
                            employee.setHealthId(health.getHealthId());
                        }
                        //专业技术职务id
                        TechnicalPosition technicalPositionByName = technicalPositionService.getTechnicalPositionByName(employeeDTO.getTechnicalPositionId());
                        if (technicalPositionByName != null) {
                            employee.setTechnicalPositionId(technicalPositionByName.getTechnicalPositionId());
                        }
                        //职（执）业资格
                        employee.setQualification(employeeDTO.getQualification());
                        //最近进入系统时间
                        employee.setIntoSysTime(new Date());
                        //人员状态id
                        EmployeeState employeeStateByName = employeeStateService.getEmployeeStateByName(employeeDTO.getEmployeeStateId());
                        if (employeeStateByName != null) {
                            employee.setEmployeeStateId(employeeStateByName.getEmployeeStateId());
                        }
                        //进入公司时间
                        employee.setIntoCompanyTime(employeeDTO.getIntoCompanyTime());
                        //进入来源Id
                        SourceEntry sourceEntryByName = sourceEntryService.getSourceEntryByName(employeeDTO.getSourceEntryId());
                        if (sourceEntryByName != null) {
                            employee.setSourceEntryId(sourceEntryByName.getSourceEntryId());
                        }
                        //户口类别ID
                        AccountType accountTypevByName = accountTypeService.getAccountTypevByName(employeeDTO.getAccountTypeId());
                        if (accountTypevByName != null) {

                            employee.setAccountTypeId(accountTypevByName.getAccountTypeId());
                        }
                        //户口所在地
                        employee.setRegisteredResidence(employeeDTO.getRegisteredResidence());
                        //专长
                        employee.setZhuanchang(employeeDTO.getZhuanchang());
                        //创建时间
                        employee.setCrateTime(new Date());
                        //更新时间
                        employee.setUpdateTime(new Date());
                        //档案所在地
                        employee.setArchivesResidence(employeeDTO.getArchivesResidence());
                        //年度考核结果
                        employee.setAnnualAssessmentResults(employeeDTO.getAnnualAssessmentResults());

                        Employee employeeByCardId = employeeService.getEmployeeByCardId(employeeDTO.getCardId());
                        if (employeeByCardId != null) {
                            employeeService.updateById(employee);
                        } else {
                            employeeService.insert(employee);
                        }

                        //插入员工与公司关系表
                        Company companyByCompanyName = companyService.getCompanyByCompanyName(employeeDTO.getCompanyName());
                        if (companyByCompanyName != null) {
                            EmpPosition empPosition = new EmpPosition();
                            empPosition.setEmpId(employee.getEmployeeId());
                            empPosition.setComId(companyByCompanyName.getCompanyId());
                            empPosition.setStatus(1);
                            empPosition.setCrateTime(new Date());
                            empPosition.setUpdateTime(new Date());
                            empPositionService.insert(empPosition);
                        }

                    }


                } else if ("教育经历".equals(sheetName)) {

                    List<EducationDTO> list = ExcelImportUtil.importExcel(files.getInputStream(), EducationDTO.class, params);
                    for (EducationDTO educationDTO : list) {
                        Education education = new Education();
                        //员工id
                        Employee employeeByCardId = employeeService.getEmployeeByCardId(educationDTO.getCardId());
                        if (employeeByCardId != null) {
                            education.setEmployeeId(employeeByCardId.getEmployeeId());
                        }
                        //学历类型ID
                        if (educationDTO.getEducationTypeId() != null) {

                            EducationType educationTypeByEducationTypeName = educationTypeService.getEducationTypeByEducationTypeName(educationDTO.getEducationTypeId());
                            if (educationTypeByEducationTypeName != null) {
                                education.setEducationTypeId(educationTypeByEducationTypeName.getEducationTypeId());
                            }
                        } else {
                            EducationType educationTypeByEducationTypeName = educationTypeService.getEducationTypeByEducationTypeName(educationDTO.getWorkEducationTypeId());
                            if (educationTypeByEducationTypeName != null) {
                                education.setEducationTypeId(educationTypeByEducationTypeName.getEducationTypeId());
                            }
                        }
                        //学位类型ID
                        if (educationDTO.getDegreeTypeId() != null) {
                            DegreeType degreeByDegreeName = degreeTypeService.getDegreeByDegreeName(educationDTO.getDegreeTypeId());
                            if (degreeByDegreeName != null) {

                                education.setDegreeTypeId(degreeByDegreeName.getDegreeTypeId());
                            }
                            //学位名称
                            education.setDegreeName(educationDTO.getDegreeTypeId());
                        } else {
                            DegreeType degreeByDegreeName = degreeTypeService.getDegreeByDegreeName(educationDTO.getWorkDegreeTypeId());
                            if (degreeByDegreeName != null) {
                                education.setDegreeTypeId(degreeByDegreeName.getDegreeTypeId());
                            }
                            education.setDegreeName(educationDTO.getWorkDegreeTypeId());

                        }
                        //入学时间
                        //学位授予日期/毕业时间
                        //毕业专业
                        if (educationDTO.getMajor() != null) {

                            education.setMajor(educationDTO.getMajor());
                        } else {
                            education.setMajor(educationDTO.getWorkMajor());
                        }
                        //学位授予单位
                        if (educationDTO.getDegreeUnit() != null) {
                            education.setDegreeUnit(educationDTO.getDegreeUnit());
                        } else {
                            education.setDegreeUnit(educationDTO.getWorkDegreeUnit());
                        }
                        //学历证书编号
                        //学位证书编号
                        //教育类型ID
                        RecordType recordTypeByRecordTypeName = recordTypeService.getRecordTypeByRecordTypeName(educationDTO.getRecordTypeId());
                        if (recordTypeByRecordTypeName != null) {

                            education.setRecordTypeId(recordTypeByRecordTypeName.getRecordTypeId());
                        }
                        //是否最高学历
                        //备注
                        //保存时间
                        education.setCreateTime(new Date());
                        //修改时间
                        education.setUpdateTime(new Date());

                        educationService.insert(education);


                    }
					/*boolean insertBatch = educationService.insertBatch(list);
					if(insertBatch) {
						return new ResponseVal(200, "success");
					}else {
						return new ResponseVal(500, "fail");
					}*/

                } else if ("工作经历".equals(sheetName)) {

                    List<WorkHistoryDTO> list = ExcelImportUtil.importExcel(files.getInputStream(), WorkHistoryDTO.class, params);

                    for (WorkHistoryDTO workHistoryDTO : list) {
                        WorkHistory workHistory = new WorkHistory();

                        Employee byCardId = employeeService.getEmployeeByCardId(workHistoryDTO.getCardId());
                        if (byCardId != null) {

                            workHistory.setEmployeeId(byCardId.getEmployeeId());
                            workHistory.setWorkHistoryName(workHistoryDTO.getWorkHistoryName());

                            workHistory.setPost(workHistoryDTO.getPost());

                            workHistory.setStartTime(workHistoryDTO.getStartTime());

                            workHistory.setLeaveTime(workHistoryDTO.getLeaveTime());

                            workHistory.setCreateTime(new Date());

                            workHistory.setUpdateTime(new Date());

                            //System.out.println(workHistory);
                            workHistoryService.insert(workHistory);
                        }


                    }

                } else if ("家庭成员社会关系".equals(sheetName)) {

                    List<FamilyMemberDTO> list = ExcelImportUtil.importExcel(files.getInputStream(), FamilyMemberDTO.class, params);

                    for (FamilyMemberDTO familyMemberDTO : list) {
                        FamilyMember familyMember = new FamilyMember();

                        //出生年月
                        familyMember.setBorthDate(familyMemberDTO.getBorthDate());
                        //称谓
                        familyMember.setCall(familyMemberDTO.getCall());
                        //创建时间
                        familyMember.setCreateTime(new Date());
                        //关联员工ID
                        Employee byCardId = employeeService.getEmployeeByCardId(familyMemberDTO.getCardId());
                        if (byCardId != null) {

                            familyMember.setEmpId(byCardId.getEmployeeId());
                        }
                        //姓名
                        familyMember.setName(familyMemberDTO.getName());
                        //政治面貌
                        familyMember.setPoliticalLook(familyMemberDTO.getPoliticalLook());
                        //修改时间
                        familyMember.setUpdateTime(new Date());
                        //工作单位及职务
                        familyMember.setWorkUnit(familyMemberDTO.getWorkUnit());
                        //人员关系表ID
                        familyMemberService.insert(familyMember);


                    }


                }

            }

            return new ResponseVal(0, "上传成功");

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseVal(500, "上传失败！");
        }

    }

    /**
     * 导入excel公司
     *
     * @param files(标题0行，表头1行)
     */
    @ApiOperation(value = "导入excel公司")
    @PostMapping("/importExcelCompany")
    @ResponseBody
    public ResponseVal importExcelCompany(@RequestParam(value = "file", required = false) MultipartFile files) {
        try {
            ImportParams params = new ImportParams();
            params.setTitleRows(0);
            params.setHeadRows(1);
            List<Company> list = ExcelImportUtil.importExcel(files.getInputStream(), Company.class, params);
            for (Company company : list) {
                company.setStatus(1);
                company.setCrateTime(new Date());
                company.setUpdateTime(new Date());
            }
            boolean insertBatch = companyService.insertBatch(list);
            if (insertBatch) {
                return new ResponseVal(0, "success");
            } else {
                return new ResponseVal(500, "fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseVal(500, "erro");
        }
    }

    @ApiOperation(value = "导出员工信息模板")
    @RequestMapping("/exportEmployeeInfoTemplate")
    @ResponseBody
    public void exportEmployeeInfoTemplate(HttpServletResponse response) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            //本地资源
            TemplateExportParams params = new TemplateExportParams("d:/static/excelTemplate/员工信息模板.xlsx", true);
            //resource下资源
            //TemplateExportParams params = new TemplateExportParams("static/excelTemplate/员工信息模板.xlsx", true);
            Workbook workbook = ExcelExportUtil.exportExcel(params, map);
            // 设置excel的文件名称
            String excelName = "员工信息模板";
            // 重置响应对象
            response.reset();
            // 指定下载的文件名--设置响应头
            response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(excelName, "UTF-8") + ".xlsx");
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            // 写出数据输出流到页面
            OutputStream output = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            workbook.write(bufferedOutPut);
            bufferedOutPut.flush();
            bufferedOutPut.close();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/zidian", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVal importExcelzidian(HttpServletRequest request,
                                         @RequestParam(value = "file", required = false) MultipartFile files) {

        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        try {
            List<DegreeType> list = ExcelImportUtil.importExcel(files.getInputStream(), DegreeType.class, params);
            for (DegreeType recordType : list) {

                recordType.setStatus(1);
                recordType.setCrateTime(new Date());
                recordType.setUpdateTime(new Date());
                degreeTypeService.insert(recordType);
            }
            return new ResponseVal(0, "ok");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseVal(500, "fail");
        }
    }

}
