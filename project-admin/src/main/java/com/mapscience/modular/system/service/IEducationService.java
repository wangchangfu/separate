package com.mapscience.modular.system.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.mapscience.modular.system.model.Education;

/**
 * <p>
 * 教育信息表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
public interface IEducationService extends IService<Education> {

	List<Education> findEducationByCompanyId(String companyId);
    
    Education getHighestDegreeByEmpId(String empId);
}
