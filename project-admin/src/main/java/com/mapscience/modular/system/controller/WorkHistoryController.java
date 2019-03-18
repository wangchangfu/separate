package com.mapscience.modular.system.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.WorkHistory;
import com.mapscience.modular.system.service.IWorkHistoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 工作经验表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@Api(tags="工作经验控制器")
@RestController
@RequestMapping("/workHistory")
public class WorkHistoryController {
	
	@Autowired
	private IWorkHistoryService service;
	
	@ApiOperation(value = "通过员工id查询工作经验")
	@PostMapping("/getWorkHistoryByEmpId")
	public ResponseVal<List<WorkHistory>> getWorkHistoryByEmpId(String empId) {
		try {
			List<WorkHistory> getWorkHistoryByEmpId = service.getWorkHistoryByEmpId(empId);
			if (ObjectUtils.isEmpty(getWorkHistoryByEmpId)) {
				return new ResponseVal<List<WorkHistory>>(500, "fail", null);
			} else {
				return new ResponseVal<List<WorkHistory>>(200, "success", getWorkHistoryByEmpId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVal<List<WorkHistory>>(500, "erro", null);
		}
	}

}

