package com.mapscience.modular.system.controller;


import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.FamilyMember;
import com.mapscience.modular.system.service.IFamilyMemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.FamilyMember;
import com.mapscience.modular.system.service.IFamilyMemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 家庭成员表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-03-14
 */
@Api(tags="家庭成员控制器")
@RestController
@RequestMapping("/familyMember")
public class FamilyMemberController {
	
	@Autowired
	private IFamilyMemberService service;

	@ApiOperation(value = "通过员工id查询家庭成员")
	@PostMapping("/getFamilyMemberByEmpId")
	public ResponseVal<List<FamilyMember>> getFamilyMemberByEmpId(String empId) {
		try {
			List<FamilyMember> getFamilyMemberByEmpId = service.getFamilyMemberByEmpId(empId);
			if (ObjectUtils.isEmpty(getFamilyMemberByEmpId)) {
				return new ResponseVal<List<FamilyMember>>(500, "fail", null);
			} else {
				return new ResponseVal<List<FamilyMember>>(0, "success", getFamilyMemberByEmpId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVal<List<FamilyMember>>(500, "erro", null);
		}
	}

}

