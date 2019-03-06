package com.mapscience.modular.system.controller;


import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.Company;
import com.mapscience.modular.system.service.ICompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 公司基本信息表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private ICompanyService companyService;
    /**
     * 查找所有的公司
     */
    @ResponseBody
    @RequestMapping("/getList")
    public ResponseVal getList(){
        List<Company> list = companyService.getList();
        return new ResponseVal(200,"查询成功",list);
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
	 * 通过Id查找公司
	 * @author zhoux
	 */
	@RequestMapping(value = "findCompanyById")
	@ResponseBody
	public ResponseVal findCompanyById(String id) {
		try {
			Company company = companyService.selectById(id);
			if(ObjectUtils.isEmpty(company)) {
				return new ResponseVal(500,"fail");
			}else {
				return new ResponseVal(200,"success");
			}
		} catch (Exception e) {
			return new ResponseVal(500,"erro");
		}
	}
	
	/**
	 * 通过Id删除公司
	 * @author zhoux
	 */
	@RequestMapping(value = "deleteCompanyById")
	@ResponseBody
	public ResponseVal deleteCompanyById(String id) {
		try {
			boolean flag = companyService.deleteById(id);
			if(flag) {
				return new ResponseVal(200,"success");
			}else {
				return new ResponseVal(500,"fail");
			}
		} catch (Exception e) {
			return new ResponseVal(500,"erro");
		}
	}
	
	/**
	 * 通过Id修改公司
	 * @author zhoux
	 */
	@RequestMapping(value = "updateCompanyById")
	@ResponseBody
	public ResponseVal updateCompanyById(Company company) {
		try {
			boolean flag = companyService.updateById(company);
			if(flag) {
				return new ResponseVal(200,"success");
			}else {
				return new ResponseVal(500,"fail");
			}
		} catch (Exception e) {
			return new ResponseVal(500,"erro");
		}
	}

}

