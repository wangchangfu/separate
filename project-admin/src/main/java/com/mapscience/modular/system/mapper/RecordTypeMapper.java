package com.mapscience.modular.system.mapper;

import com.mapscience.modular.system.model.RecordType;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 教育类型表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
public interface RecordTypeMapper extends BaseMapper<RecordType> {

    /**
     * 根据教育类型名获取教育类型对象
     * @param recordTypeName
     * @return
     */
    RecordType getRecordTypeByRecordTypeName(String recordTypeName);

    /**
     * 获取所有
     * @return
     */
    List<RecordType> getList();

}
