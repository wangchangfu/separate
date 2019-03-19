package com.mapscience.modular.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.CompanyType;

/**
 * <p>
 * 公司业务类型表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
public interface ICompanyTypeService extends IService<CompanyType> {

    /**
     * 查询行业类别
     * @return
     */
    ResponseVal findComType();
}
