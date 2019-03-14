package com.mapscience.modular.system.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.mapscience.modular.system.model.FamilyMember;

/**
 * <p>
 * 家庭成员表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-03-14
 */
public interface IFamilyMemberService extends IService<FamilyMember> {
	
	List<FamilyMember> getFamilyMemberByEmpId(String empId);

}
