package com.mapscience.modular.system.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.Company;
import com.mapscience.modular.system.service.ICompanyService;

import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 公司基本信息表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private ICompanyService companyService;
    
    @ApiOperation("查找所有的公司")
    @GetMapping("/getAllCompany")
    public ResponseVal getAllCompany(){
    	try {
    		 List<Company> list = companyService.getList();
    	     return new ResponseVal(200,"success",list);
		} catch (Exception e) {
			return new ResponseVal(200,"erro",null);
		}
    }

    @ApiOperation("查询公司及子公司")
    @GetMapping("/findComTreeAndChidren")
    public ResponseVal findComTreeAndChidren(Company company){
       return this.companyService.findComTree(company);
    }

    @ApiOperation("添加公司")
    @PostMapping("/saveCompany")
    public ResponseVal saveCompany(Company company){
       return this.companyService.saveCompany(company);
    }
    
    @ApiOperation(value = "通过id删除公司")
	@DeleteMapping(value = "/deleteCompanyById")
	public ResponseVal deleteCompanyById(String id) {
    	try {
			boolean deleteById = companyService.deleteById(id);
			if(deleteById) {
				return new ResponseVal(200,"success");
			}else {
				return new ResponseVal(500,"fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVal(500,"erro");
		}
	}
	
	@ApiOperation(value = "通过id修改公司")
	@PutMapping(value = "/updateCompanyById")
	public ResponseVal updateCompanyById(Company entity) {
		try {
			boolean flag = companyService.updateAllColumnById(entity);
			if(flag) {
				return new ResponseVal(200,"success");
			}else {
				return new ResponseVal(500,"fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVal(500,"erro");
		}
	}
	
	@ApiOperation(value = "通过id查询公司")
	@GetMapping(value = "/selectCompanyById")
	public ResponseVal selectCompanyById(String id) {
		try {
			Company selectById = companyService.selectById(id);
			if(ObjectUtils.isEmpty(selectById)) {
				return new ResponseVal(500,"fail",null);
			}else {
				return new ResponseVal(200,"success",selectById);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVal(500,"erro",null);
		}
	}
	
    @ApiOperation("查询公司部门树")
    @GetMapping("/findCompanyAndDepartmentTree")
    public ResponseVal findCompanyAndDepartmentTree(){
    	try {
			List<Object> findCompanyAndDepartmentTree = companyService.findCompanyAndDepartmentTree();
			return new ResponseVal(200,"success",findCompanyAndDepartmentTree);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVal(500,"erro",null);
		}
    }

}