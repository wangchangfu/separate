package com.mapscience.modular.system.mapper;

import com.mapscience.modular.system.model.FamilyMember;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 家庭成员表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-03-14
 */
public interface FamilyMemberMapper extends BaseMapper<FamilyMember> {
	
	List<FamilyMember> getFamilyMemberByEmpId(String empId);


	List<FamilyMember> getList();

}
