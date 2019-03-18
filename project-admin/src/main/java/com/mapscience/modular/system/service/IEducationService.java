package com.mapscience.modular.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.Company;
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

    /**
     * 学历分布
     * @param company
     * @return
     */
    ResponseVal findEducationt(Company company);
    
    Education getHighestDegreeByEmpId(String empId);
}
