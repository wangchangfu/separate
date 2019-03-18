package com.mapscience.modular.system.controller;


import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.WorkHistory;
import com.mapscience.modular.system.service.IWorkHistoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 工作经验表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@RestController
@RequestMapping("/workHistory")
public class WorkHistoryController {
	

	@Autowired
	private IWorkHistoryService service;
	
	
	@ApiOperation(value = "通过员工id查询工作经验")
	@GetMapping("/getWorkHistoryByEmpId")
	public ResponseVal getWorkHistoryByEmpId(String empId) {
		try {
			List<WorkHistory> getWorkHistoryByEmpId = service.getWorkHistoryByEmpId(empId);
			if (ObjectUtils.isEmpty(getWorkHistoryByEmpId)) {
				return new ResponseVal(500, "fail", null);
			} else {
				return new ResponseVal(200, "success", getWorkHistoryByEmpId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVal(500, "erro", null);
		}
	}

}

