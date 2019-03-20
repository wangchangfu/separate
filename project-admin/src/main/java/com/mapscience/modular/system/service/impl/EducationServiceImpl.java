package com.mapscience.modular.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mapscience.modular.system.mapper.EducationMapper;
import com.mapscience.modular.system.model.Company;
import com.mapscience.modular.system.model.Education;
import com.mapscience.modular.system.model.Education.是否最高学历;
import com.mapscience.modular.system.service.IEducationService;

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
	

	@Override
	public Education getHighestDegreeByEmpId(String employeeId) {
		Education rs = null;
		Education education = new Education();
		education.setEmployeeId(employeeId);
		education.setIsHighestDegree(是否最高学历.是.getValue());
		List<Education> findEducation = mapper.findEducation(education, null);
		if(!ObjectUtils.isEmpty(findEducation)) {
			rs = findEducation.get(0);
		}
		return rs;
	}

	@Override
	public List<Education> findEducationByCompanyId(String companyId) {
		return mapper.findEducation(null, companyId);
	}
}
