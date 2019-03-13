package com.mapscience.modular.system.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.Department;
import com.mapscience.modular.system.service.IDepartmentService;

import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {
	
	 @Autowired
	 private IDepartmentService service;
	 
    /**
     * 查找所有的部门
     */
	@ApiOperation(value = "查找所有的部门")
	@GetMapping("/getAllDepartment")
	public ResponseVal getAllDepartment() {
		try {
			List<Department> selectList = service.selectList(null);
			return new ResponseVal(200, "success", selectList);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVal(500, "erro", null);
		}
	}
	
    /**
     * 增加
     */
    @ResponseBody
    @RequestMapping("insert")
    public ResponseVal insert(Department entity){
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
    
	/**
	 * 根据id删除
	 */
	@RequestMapping(value = "deleteById")
	@ResponseBody
	public ResponseVal deleteById(String id) {
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
	
	/**
	 * 根据id修改
	 */
	@RequestMapping(value = "updateById")
	@ResponseBody
	public ResponseVal updateById(Department entity) {
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
	
	/**
	 * 根据Id查询
	 */
	@RequestMapping(value = "selectById")
	@ResponseBody
	public ResponseVal selectById(String id) {
		try {
			Department selectById = service.selectById(id);
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

