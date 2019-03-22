package com.mapscience.modular.system.mapper;

import com.mapscience.modular.system.model.TechnicalPosition;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 专业技术职务表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
public interface TechnicalPositionMapper extends BaseMapper<TechnicalPosition> {

    /**
     * 根据专业技术职务名称查找
     *
     * @param technicalPositionName
     * @return
     */
    TechnicalPosition getTechnicalPositionByName(String technicalPositionName);

    /**
     * 根据专业技术职务ID查找
     *
     * @param technicalPositionId
     * @return
     */
    TechnicalPosition getTechnicalPositionById(String technicalPositionId);


}
