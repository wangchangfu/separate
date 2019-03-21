package com.mapscience.modular.system.service;

import com.mapscience.modular.system.model.TechnicalPosition;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 专业技术职务表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
public interface ITechnicalPositionService extends IService<TechnicalPosition> {

    TechnicalPosition getTechnicalPositionByName(String technicalPositionName);


}
