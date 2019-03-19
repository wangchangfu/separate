package com.mapscience.modular.system.controller;


import com.mapscience.core.base.controller.BaseController;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.Company;
import com.mapscience.modular.system.model.CompanyType;
import com.mapscience.modular.system.model.Menu;
import com.mapscience.modular.system.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前端大屏控制器
 */
@Api(tags="大屏显示")
@Controller
@CrossOrigin
@RequestMapping("homeStatistics")
public class HomeStatisticsController extends BaseController {

    /**
     * 公司
     */
    @Autowired
    private ICompanyService companyService;

    /**
     * 角色
     */
    @Autowired
    private IRoleService roleService;
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
     *
     * 菜单
     */
    @Autowired
    private IMenuService menuService;
    /**
     * 查询公司信息及坐标
     * @param company
     * @return
     */
    @ApiOperation(value = "查询公司信息及坐标")
    @RequestMapping(value="findOrgList",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVal<List<Company>> findComList(Company company){
        ResponseVal<List<Company>> comList = this.companyService.findComList(company);
        return  comList;
    }


    /**
     * 查询员工合同分布
     * @return
     */
    @ApiOperation(value = "查询员工合同分布")
    @RequestMapping(value = "findContract",method = RequestMethod.POST)
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
    @RequestMapping(value = "findEducationt",method = RequestMethod.POST)
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
    @RequestMapping(value = "getEmpCount",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVal getEmpCount(Company company){
        return this.employeeService.getEmpCount(company);
    }


    /**
     * 查询行业类别
     * @return
     */
    @ApiOperation(value = "查询公司行业类别")
    @RequestMapping(value = "findComType",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVal<List<CompanyType>> findComType(){
        ResponseVal<List<CompanyType>> comType = this.companyTypeService.findComType();
        return comType;
    }

    /**
     * 根据菜单ID返回菜单树
     * @return
     */
    @ApiOperation(value = "根据菜单ID返回菜单树")
    @RequestMapping(value = "/modelIndex",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVal modelIndex(@RequestBody Menu menu) {
        //获取当前用户
        //ShiroUser shiroUser = ShiroKit.getUser();
        //String account = shiroUser.getAccount();
        //查询当前用户角色
       // List<Menu> menus = this.menuService.findMenus(menu,shiroUser.getId());
        //根据当前用户查找角色
        //this.
        //return new ResponseVal("查找成功",menus);

        //根据菜单ID查询菜单
        ResponseVal chind = this.menuService.findChind(menu);
        return chind;
    }

}
