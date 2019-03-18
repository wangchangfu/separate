package com.mapscience.modular.system.controller;


import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.Education;
import com.mapscience.modular.system.service.IEducationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.Api;

/**
 * <p>
 * 教育信息表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@Api(tags="教育信息控制器")
@RestController
@RequestMapping("/education")
public class EducationController {

	@Autowired
	private IEducationService service;

	@ApiOperation(value = "通过员工id查询最高学历")
	@PostMapping("/getHighestDegreeByEmpId")
	public ResponseVal<Education> getHighestDegreeByEmpId(String empId) {
		try {
			Education getHighestDegreeByEmpId = service.getHighestDegreeByEmpId(empId);
			if (ObjectUtils.isEmpty(getHighestDegreeByEmpId)) {
				return new ResponseVal<Education>(500, "fail", null);
			} else {
				return new ResponseVal<Education>("success", getHighestDegreeByEmpId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVal<Education>(500, "erro", null);
		}
	}

}

