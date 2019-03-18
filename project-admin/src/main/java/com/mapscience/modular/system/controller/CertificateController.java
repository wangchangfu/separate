package com.mapscience.modular.system.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.Certificate;
import com.mapscience.modular.system.service.ICertificateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 员工证照表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@Api(tags="员工证照控制器")
@RestController
@RequestMapping("/certificate")
public class CertificateController {
	
	@Autowired
	private ICertificateService service;
	
	@ApiOperation(value = "通过员工id查询证书经历")
	@PostMapping("/getCertificateByEmpId")
	public ResponseVal<List<Certificate>> getCertificateByEmpId(String empId) {
		try {
			List<Certificate> getCertificateByEmpId = service.getCertificateByEmpId(empId);
			if (ObjectUtils.isEmpty(getCertificateByEmpId)) {
				return new ResponseVal<List<Certificate>>(500, "fail", null);
			} else {
				return new ResponseVal<List<Certificate>>(200, "success", getCertificateByEmpId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVal<List<Certificate>>(500, "erro", null);
		}
	}


}

