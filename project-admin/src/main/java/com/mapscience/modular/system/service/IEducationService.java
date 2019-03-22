package com.mapscience.modular.system.service;

import java.util.HashMap;

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

	HashMap<String, Integer> findEducationByCompanyId(String companyId);
    
    Education getHighestDegreeByEmpId(String empId);

    /**
     * 插入
     * @param education
     * @return
     */
    //ResponseVal saveEducation(Education education);
}
