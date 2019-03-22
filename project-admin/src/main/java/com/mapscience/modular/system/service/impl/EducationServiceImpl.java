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
		Education education = new Education();
		education.setEmployeeId(employeeId);
		education.setIsHighestDegree(isHighestDegree.是最高学历.getValue());
		List<Education> findEducation = mapper.findEducation(education, null);
		if(!ObjectUtils.isEmpty(findEducation)) {
			rs = findEducation.get(0);
		}
		return rs;
	}

	/**
	 * 查询所有Education
	 * @return
	 */
	@Override
	public List<Education> getList() {
		return this.baseMapper.getList();
	}

	@Override
	public HashMap<String, Integer> findEducationByCompanyId(String companyId) {
		HashMap<String, Integer> newHashMap = Maps.newHashMap();
		List<EducationType> selectList = educationTypeService.selectList(null);
		Education education = new Education();
		//education.setIsHighestDegree(isHighestDegree.是最高学历.getValue());
		for (EducationType educationType : selectList) {
			education.setEducationTypeId(educationType.getEducationTypeId());
			newHashMap.put(educationType.getEducationTypeName(), mapper.findEducation(education, companyId).size());
		}
		return newHashMap;
	}
}
