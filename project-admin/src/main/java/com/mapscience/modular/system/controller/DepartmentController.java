package com.mapscience.modular.system.controller;


import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.Department;
import com.mapscience.modular.system.service.IDepartmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	 
	@ApiOperation("查询所有部门")
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
	
	@ApiOperation("添加部门")
    @PostMapping("/insertDepartment")
    public ResponseVal insertDepartment(Department entity){
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
    
	@ApiOperation("根据id删除部门")
	@DeleteMapping("/deleteDepartmentById")
	public ResponseVal deleteDepartmentById(String id) {
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
	
	@ApiOperation("修改部门")
	@PutMapping("/updateDepartment")
	public ResponseVal updateDepartment(Department entity) {
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
	
	@ApiOperation("根据Id查询部门")
	@GetMapping("/selectDepartmentById")
	public ResponseVal selectDepartmentById(String id) {
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

