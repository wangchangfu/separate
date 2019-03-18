package com.mapscience.modular.system.controller;


import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.util.JedisUtil;
import com.mapscience.modular.system.model.Company;
import com.mapscience.modular.system.service.ICompanyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 公司基本信息表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@Api(tags="公司控制器")
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private ICompanyService companyService;
    
    @ApiOperation("查找所有的公司")
    @PostMapping("/getAllCompany")
    public ResponseVal<List<Company>> getAllCompany(){
    	try {
    		 List<Company> list = companyService.getList();
    	     return new ResponseVal<List<Company>>(0,"success",list);
		} catch (Exception e) {
			return new ResponseVal<List<Company>>(500,"erro",null);
		}
    }

    @ApiOperation("查询公司及子公司")
    @PostMapping("/findComTreeAndChidren")
    public ResponseVal<List<Company>> findComTreeAndChidren(Company company){
       return this.companyService.findComTree(company);
    }

    @ApiOperation("添加公司")
    @PostMapping("/saveCompany")
    public ResponseVal saveCompany(Company company){

       return this.companyService.saveCompany(company);

    }
    
    @ApiOperation(value = "通过id删除公司")
    @PostMapping(value = "/deleteCompanyById")
	public ResponseVal deleteCompanyById(String id) {
    	try {
			boolean deleteById = companyService.deleteById(id);
			if(deleteById) {
				return new ResponseVal(0,"success");
			}else {
				return new ResponseVal(500,"fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVal(500,"erro");
		}
	}
	
	@ApiOperation(value = "通过id修改公司")
	@PostMapping(value = "/updateCompanyById")
	public ResponseVal updateCompanyById(Company entity) {
		try {
			boolean flag = companyService.updateAllColumnById(entity);
			if(flag) {
				return new ResponseVal(0,"success");
			}else {
				return new ResponseVal(500,"fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVal(500,"erro");
		}
	}
	
	@ApiOperation(value = "通过id查询公司")
	@PostMapping(value = "/selectCompanyById")
	public ResponseVal<Company> selectCompanyById(String id) {
		try {
			Company selectById = companyService.selectById(id);
			if(ObjectUtils.isEmpty(selectById)) {
				return new ResponseVal<Company>(500,"fail",null);
			}else {
				return new ResponseVal<Company>(0,"success",selectById);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVal<Company>(500,"erro",null);
		}
	}
	
	@ApiOperation("查询公司部门树")
    @PostMapping("/findCompanyAndDepartmentTree")
    public ResponseVal<List<Object>> findCompanyAndDepartmentTree(){
    	try {
    		
    		@SuppressWarnings("unchecked")
			List<Object> objectList = (List<Object>) JedisUtil.getObject("organize:companyAndDepartmentTree");
        	return new ResponseVal<List<Object>>(0,"success", objectList);
		} catch (Exception e) {
			try {
				List<Object> findCompanyAndDepartmentTree = companyService.findCompanyAndDepartmentTree();
				JedisUtil.setObject("organize:companyAndDepartmentTree", findCompanyAndDepartmentTree);
				return new ResponseVal<List<Object>>(0,"success",findCompanyAndDepartmentTree);
			} catch (Exception e2) {
				e2.printStackTrace();
				return new ResponseVal<List<Object>>(500,"erro",null);
			}
		}
    }

}