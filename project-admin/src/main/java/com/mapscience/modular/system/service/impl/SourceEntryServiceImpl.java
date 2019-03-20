package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.model.SourceEntry;
import com.mapscience.modular.system.mapper.SourceEntryMapper;
import com.mapscience.modular.system.service.ISourceEntryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 进入公司途径来源表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@Service
public class SourceEntryServiceImpl extends ServiceImpl<SourceEntryMapper, SourceEntry> implements ISourceEntryService {

    @Autowired
    private SourceEntryMapper sourceEntryMapper;

    @Override
    public SourceEntry getSourceEntryByName(String sourceEntryName) {
        return sourceEntryMapper.getSourceEntryByName(sourceEntryName);
    }
}
