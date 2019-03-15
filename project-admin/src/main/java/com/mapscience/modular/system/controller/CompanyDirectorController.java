package com.mapscience.modular.system.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.CompanyDirector;
import com.mapscience.modular.system.service.ICompanyDirectorService;

import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 公司权力机构信息表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@RestController
@RequestMapping("/companyDirector")
public class CompanyDirectorController {
	
	@Autowired
	private ICompanyDirectorService service;
	 
	@ApiOperation("查询所有公司权力机构信息")
	@GetMapping("/getAllCompanyDirector")
	public ResponseVal getAllCompanyDirector() {
		try {
			List<CompanyDirector> selectList = service.selectList(null);
			return new ResponseVal(200, "success", selectList);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVal(500, "erro", null);
		}
	}
	
	@ApiOperation("添加公司权力机构信息")
    @PostMapping("/insertCompanyDirector")
    public ResponseVal insertCompanyDirector(CompanyDirector entity){
		try {
			boolean flag = service.insert(entity);
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
    
	@ApiOperation("根据id删除公司权力机构信息")
	@DeleteMapping("/deleteCompanyDirectorById")
	public ResponseVal deleteCompanyDirectorById(String id) {
		try {
			boolean flag = service.deleteById(id);
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
	
	@ApiOperation("修改公司权力机构信息")
	@PutMapping("/updateCompanyDirector")
	public ResponseVal updateCompanyDirector(CompanyDirector entity) {
		try {
			boolean flag = service.updateById(entity);
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
	
	@ApiOperation("根据Id查询公司权力机构信息")
	@GetMapping("/selectCompanyDirectorById")
	public ResponseVal selectCompanyDirectorById(String id) {
		try {
			CompanyDirector selectById = service.selectById(id);
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

