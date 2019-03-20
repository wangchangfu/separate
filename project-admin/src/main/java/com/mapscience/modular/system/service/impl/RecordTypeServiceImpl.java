package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.model.RecordType;
import com.mapscience.modular.system.mapper.RecordTypeMapper;
import com.mapscience.modular.system.service.IRecordTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 教育类型表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@Service
public class RecordTypeServiceImpl extends ServiceImpl<RecordTypeMapper, RecordType> implements IRecordTypeService {


    @Autowired
    private RecordTypeMapper recordTypeMapper;

    @Override
    public RecordType getRecordTypeByRecordTypeName(String recordTypeName) {
        return recordTypeMapper.getRecordTypeByRecordTypeName(recordTypeName);
    }

    @Override
    public List<RecordType> getList() {
        return recordTypeMapper.getList();
    }
}
