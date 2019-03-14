package com.mapscience.modular.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mapscience.modular.system.mapper.FamilyMemberMapper;
import com.mapscience.modular.system.model.FamilyMember;
import com.mapscience.modular.system.service.IFamilyMemberService;

/**
 * <p>
 * 家庭成员表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-03-14
 */
@Service
public class FamilyMemberServiceImpl extends ServiceImpl<FamilyMemberMapper, FamilyMember> implements IFamilyMemberService {
	
	@Autowired
    private FamilyMemberMapper mapper;

	@Override
	public List<FamilyMember> getFamilyMemberByEmpId(String empId) {
		return mapper.getFamilyMemberByEmpId(empId);
	}

}
