package com.mapscience.modular.system.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.mapscience.modular.system.mapper.EducationMapper;
import com.mapscience.modular.system.model.Education;
import com.mapscience.modular.system.model.Education.isHighestDegree;
import com.mapscience.modular.system.model.EducationType;
import com.mapscience.modular.system.service.IEducationService;
import com.mapscience.modular.system.service.IEducationTypeService;

/**
 * <p>
 * 教育信息表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@Service	
public class EducationServiceImpl extends ServiceImpl<EducationMapper, Education> implements IEducationService {

	@Autowired
    private EducationMapper mapper;
	
	@Autowired
    private IEducationTypeService educationTypeService;
	

	@Override
	public Education getHighestDegreeByEmpId(String employeeId) {
		Education rs = null;
		HashMap<String, String> params = Maps.newHashMap();
		params.put("employeeId", employeeId);
		params.put("isHighestDegree", isHighestDegree.是最高学历.getValue() + "");
		List<Education> findEducation = mapper.findEducation(params);
		if(!ObjectUtils.isEmpty(findEducation)) {
			rs = findEducation.get(0);
		}
		return rs;
	}

	@Override
	public HashMap<String, String> findEducationByCompanyId(String companyId) {
		HashMap<String, String> newHashMap = Maps.newHashMap();
		List<EducationType> selectList = educationTypeService.selectList(null);
		HashMap<String, String> params = Maps.newHashMap();
		params.put("companyId", companyId);
		params.put("isHighestDegree", isHighestDegree.是最高学历.getValue() + "");
		for (EducationType educationType : selectList) {
			params.put("educationTypeId", educationType.getEducationTypeId());
			newHashMap.put(educationType.getEducationTypeName(), mapper.findEducation(params).size() + "");
		}
		return newHashMap;
	}
}
