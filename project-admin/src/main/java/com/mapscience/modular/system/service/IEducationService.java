package com.mapscience.modular.system.service;

import java.util.HashMap;
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

	HashMap<String, String> findEducationByCompanyId(String companyId);
    
    Education getHighestDegreeByEmpId(String empId);

    /**
     * 插入
     * @param education
     * @return
     */
    //ResponseVal saveEducation(Education education);

    /**
     * 查询所有education
     * @return
     */
    List<Education> getList();
}
