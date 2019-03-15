package com.mapscience.modular.system.controller;


import com.mapscience.core.base.controller.BaseController;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.Company;
import com.mapscience.modular.system.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 前端大屏控制器
 */
@Api(value ="大屏显示")
@Controller
@RequestMapping("/homeStatistics/")
public class HomeStatisticsController extends BaseController {

    /**
     * 公司
     */
    @Autowired
    private ICompanyService companyService;

    /**
     * 人员
     */
    @Autowired
    private IEmployeeService employeeService;
    /**
     * 行业类别
     */
    @Autowired
    private ICompanyTypeService companyTypeService;
    /**
     * 学历分布
     */
    private IEducationService educationService;
    /**
     * 合同
     */
    @Autowired
    private IContractManagementService contractManagementService;
    /**
     * 查询公司信息及坐标
     * @param company
     * @return
     */
    @ApiOperation(value = "查询公司信息及坐标")
    @RequestMapping("findOrgList")
    @ResponseBody
    public ResponseVal findComList(Company company){

        return  this.companyService.findComList(company);
    }


    /**
     * 查询员工合同分布
     * @return
     */
    @ApiOperation(value = "查询员工合同分布")
    @RequestMapping("findContract")
    @ResponseBody
    public ResponseVal findContract(Company company){
        return this.contractManagementService.findContract(company);
    }


    /**
     * 员工学历分布
     * @param company
     * @return
     */
    @ApiOperation(value = "员工学历分布")
    @RequestMapping("findEducationt")
    @ResponseBody
    public ResponseVal findEducationt(Company company){
        return this.educationService.findEducationt(company);
    }

    /**
     * 统计公司人员
     * @param company
     * @return
     */
    @ApiOperation(value = "统计公司人员")
    @RequestMapping("getEmpCount")
    @ResponseBody
    public ResponseVal getEmpCount(Company company){
        return this.employeeService.getEmpCount(company);
    }


    /**
     * 查询行业类别
     * @return
     */
    @ApiOperation(value = "查询公司行业类别")
    @RequestMapping("findComType")
    @ResponseBody
    public ResponseVal findComType(){
       return this.companyTypeService.findComType();
    }

}
