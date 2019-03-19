package com.mapscience.modular.system.service;

import com.mapscience.modular.system.model.RecordType;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 教育类型表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
public interface IRecordTypeService extends IService<RecordType> {

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
