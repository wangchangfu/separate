package com.mapscience.modular.system.controller;


import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.Company;
import com.mapscience.modular.system.service.ICompanyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    /**
     * 查找所有的公司
     */
    @ApiOperation(value = "查找所有的公司")
    @GetMapping("/getAllCompany")
    public ResponseVal getAllCompany(){
    	try {
    		 List<Company> list = companyService.getList();
    	     return new ResponseVal(200,"success",list);
		} catch (Exception e) {
			return new ResponseVal(200,"erro",null);
		}
    }

    /**
     * 查询公司及子公司
     * @param company
     * @return
     */
    @RequestMapping("findComTree")
    @ResponseBody
    public ResponseVal findComTree(Company company){
       return this.companyService.findComTree(company);
    }

    /**
     * 添加公司
     * @return
     */
    @ResponseBody
    @RequestMapping("saveCompany")
    public ResponseVal saveCompany(Company company){
       return this.companyService.saveCompany(company);
    }
    
	/**
	 * 根据id删除
	 */
	@RequestMapping(value = "deleteById")
	@ResponseBody
	public ResponseVal deleteById(String id) {
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
	
	/**
	 * 根据id修改
	 */
	@RequestMapping(value = "updateById")
	@ResponseBody
	public ResponseVal updateById(Company entity) {
		try {
			boolean flag = companyService.updateById(entity);
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
	
	/**
	 * 根据Id查询
	 */
	@RequestMapping(value = "selectById")
	@ResponseBody
	public ResponseVal selectById(String id) {
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

}

