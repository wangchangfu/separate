package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.model.PoliticalStatus;
import com.mapscience.modular.system.mapper.PoliticalStatusMapper;
import com.mapscience.modular.system.service.IPoliticalStatusService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 政治面貌表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@Service
public class PoliticalStatusServiceImpl extends ServiceImpl<PoliticalStatusMapper, PoliticalStatus> implements IPoliticalStatusService {

    @Autowired
    private PoliticalStatusMapper politicalStatusMapper;


    @Override
    public PoliticalStatus getPoliticalStatusByName(String politicalStatusName) {
        return politicalStatusMapper.getPoliticalStatusByName(politicalStatusName);
    }

    @Override
    public PoliticalStatus getPoliticalStatusById(String politicalStatusId) {
        return this.baseMapper.getPoliticalStatusById(politicalStatusId);
    }
}
