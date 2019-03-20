package com.mapscience.modular.system.service;

import com.mapscience.modular.system.model.NationalityType;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 国籍表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
public interface INationalityTypeService extends IService<NationalityType> {

    NationalityType getnationalityTypeByName(String nationalityStypeName);

}
