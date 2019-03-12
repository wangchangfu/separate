package com.mapscience.modular.system.controller;


import com.mapscience.core.base.controller.BaseController;
import com.mapscience.modular.system.service.ICompanyService;
import com.mapscience.modular.system.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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


 /*   *//**
     * 查询公司信息及坐标
     * @param company
     * @return
     *//*
    @RequestMapping("findOrgList")
    @ResponseBody
    public ResponseVal findComList(Company company){

        return  this.companyService.findComList(company);
    }

    *//**
     * 查询公司的3个集团
     * @return
     *//*
    @RequestMapping("findOrgListForOne")
    @ResponseBody
    public ResponseVal findOrgListForOne(){
        return this.companyService.findOrgListForOne();
    }*/

}
