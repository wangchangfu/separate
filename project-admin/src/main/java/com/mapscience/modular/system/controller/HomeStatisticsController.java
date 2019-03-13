package com.mapscience.modular.system.controller;


import com.mapscience.core.base.controller.BaseController;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.Company;
import com.mapscience.modular.system.service.ICompanyService;
import com.mapscience.modular.system.service.IContractManagementService;
import com.mapscience.modular.system.service.IEducationService;
import com.mapscience.modular.system.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 前端大屏控制器
 */
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
    @RequestMapping("findOrgList")
    @ResponseBody
    public ResponseVal findComList(Company company){

        return  this.companyService.findComList(company);
    }


    /**
     * 查询员工合同分布
     * @return
     */
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
    @RequestMapping("getEmpCount")
    @ResponseBody
    public ResponseVal getEmpCount(Company company){
        return this.employeeService.getEmpCount(company);
    }

}
