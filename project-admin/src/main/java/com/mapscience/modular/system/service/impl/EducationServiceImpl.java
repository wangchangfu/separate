package com.mapscience.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.mapper.EducationMapper;
import com.mapscience.modular.system.model.Company;
import com.mapscience.modular.system.model.Education;
import com.mapscience.modular.system.service.IEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
    /**
     * 查询学历分布
     * @param company
     * @return
     */
    @Override
    public ResponseVal findEducationt(Company company) {
        return null;
    }

	@Override
	public Education getHighestDegreeByEmpId(String empId) {
		return mapper.getHighestDegreeByEmpId(empId);
	}
}
