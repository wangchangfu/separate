package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.model.TechnicalPosition;
import com.mapscience.modular.system.mapper.TechnicalPositionMapper;
import com.mapscience.modular.system.service.ITechnicalPositionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 专业技术职务表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@Service
public class TechnicalPositionServiceImpl extends ServiceImpl<TechnicalPositionMapper, TechnicalPosition> implements ITechnicalPositionService {


    @Autowired
    private TechnicalPositionMapper technicalPositionMapper;

    /**
     * 根据专业技术职务名称查找
     * @param technicalPositionName
     * @return
     */
    @Override
    public TechnicalPosition getTechnicalPositionByName(String technicalPositionName) {
        return technicalPositionMapper.getTechnicalPositionByName(technicalPositionName);
    }

    /**
     * 根据专业技术职务ID查找
     * @param technicalPositionId
     * @return
     */
    @Override
    public TechnicalPosition getTechnicalPositionById(String technicalPositionId) {
        return this.baseMapper.getTechnicalPositionById(technicalPositionId);
    }
}
