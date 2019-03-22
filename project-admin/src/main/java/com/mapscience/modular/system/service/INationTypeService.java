package com.mapscience.modular.system.service;

import com.mapscience.modular.system.model.NationType;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 民族表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
public interface INationTypeService extends IService<NationType> {


    NationType getNationTypeByName(String nationStypeName);

    NationType getNationTypeById(String nationTypeId);

}
