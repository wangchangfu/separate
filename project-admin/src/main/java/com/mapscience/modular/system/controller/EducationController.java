package com.mapscience.modular.system.controller;


import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.Education;
import com.mapscience.modular.system.service.IEducationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 教育信息表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@RestController
@RequestMapping("/education")
public class EducationController {
	

	@Autowired
	private IEducationService service;
	
	
	@ApiOperation(value = "通过员工id查询最高学历")
	@GetMapping("/getHighestDegreeByEmpId")
	public ResponseVal getHighestDegreeByEmpId(String empId) {
		try {
			Education getHighestDegreeByEmpId = service.getHighestDegreeByEmpId(empId);
			if (ObjectUtils.isEmpty(getHighestDegreeByEmpId)) {
				return new ResponseVal(500, "fail", null);
			} else {
				return new ResponseVal(200, "success", getHighestDegreeByEmpId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVal(500, "erro", null);
		}
	}

}

