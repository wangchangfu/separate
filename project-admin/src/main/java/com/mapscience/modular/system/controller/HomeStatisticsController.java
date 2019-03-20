package com.mapscience.modular.system.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mapscience.core.base.controller.BaseController;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.util.ObjectUtil;
import com.mapscience.modular.system.dto.MenuDTO;
import com.mapscience.modular.system.model.Company;
import com.mapscience.modular.system.model.CompanyType;
import com.mapscience.modular.system.model.Education;
import com.mapscience.modular.system.model.Employee;
import com.mapscience.modular.system.model.Menu;
import com.mapscience.modular.system.model.Role;
import com.mapscience.modular.system.service.ICompanyService;
import com.mapscience.modular.system.service.ICompanyTypeService;
import com.mapscience.modular.system.service.IContractManagementService;
import com.mapscience.modular.system.service.IEducationService;
import com.mapscience.modular.system.service.IEmployeeService;
import com.mapscience.modular.system.service.IMenuService;
import com.mapscience.modular.system.service.IRoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 前端大屏控制器
 */
@Api(tags="大屏显示")
@Controller
@RequestMapping("/homeStatistics/")
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
    @Autowired
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
     * 通过公司id查询员工合同分布
     * @return
     */
    @ApiOperation(value = "通过公司id查询员工合同分布")
    @RequestMapping(value = "findContractByCompanyId",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVal findContractByCompanyId(String companyId, 
    		@RequestParam(required=false, defaultValue="固定期,无固定,实习协议,劳务合同,其他") String contractType,
    		@RequestParam(required=false, defaultValue="3") int numberOfYearAgo){
    	try {
    		ArrayList<HashMap<String, Integer>> findContractByCompanyId = contractManagementService.findContractByCompanyId(companyId, contractType, numberOfYearAgo);
    		return new ResponseVal(0,"success",findContractByCompanyId);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVal(500,"erro",null);
		}
    }


    /**
     * 通过公司id查询员工学历分布
     * @return
     */
    @ApiOperation(value = "通过公司id查询员工学历分布")
    @RequestMapping(value = "findEducationByCompanyId",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVal<List<Education>> findEducationByCompanyId(String companyId){
    	try {
    		List<Education> findEducationByCompanyId = educationService.findEducationByCompanyId(companyId);
    		return new ResponseVal<List<Education>>(0,"success",findEducationByCompanyId);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVal<List<Education>>(500,"erro",null);
		}
    }

    /**
     * 通过公司id查询查询公司人员
     * @return
     */
    @ApiOperation(value = "通过公司id查询查询公司人员")
    @RequestMapping(value = "findEmployeeByCompanyId",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVal<List<Employee>> findEmployeeByCompanyId(String companyId){
    	try {
    		List<Employee> findEmployeeByCompanyId = employeeService.findEmployeeByCompanyId(companyId);
    		return new ResponseVal<List<Employee>>(0,"success",findEmployeeByCompanyId);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVal<List<Employee>>(500,"erro",null);
		}
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
    public ResponseVal modelIndex(@RequestBody MenuDTO menu) {
        Role byRoleId = this.roleService.findByRoleId(menu.getUserId());
            //根据菜单ID查找当前用户的菜单
        List<Menu> menus = this.menuService.findMenus(menu.getMenuId(),byRoleId.getRoleId());
        if (ObjectUtil.isEmpty(menus) || menus.size()<0){
            return new ResponseVal(HttpStatus.FOUND.value(),"暂无数据");
        }
        //根据菜单ID查询菜单
        return new ResponseVal("查询成功",menus);
    }

}

