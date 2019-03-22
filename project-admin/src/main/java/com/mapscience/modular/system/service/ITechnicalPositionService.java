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

    /**
     * 根据专业技术职务名称查找
     * @param technicalPositionName
     * @return
     */
    TechnicalPosition getTechnicalPositionByName(String technicalPositionName);

    /**
     * 根据专业技术职务Id查找
     * @param technicalPositionId
     * @return
     */
    TechnicalPosition getTechnicalPositionById(String technicalPositionId);


}
