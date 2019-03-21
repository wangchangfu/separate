package com.mapscience.modular.system.mapper;

import com.mapscience.modular.system.model.NationType;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 民族表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
public interface NationTypeMapper extends BaseMapper<NationType> {

    NationType getNationTypeByName(String nationStypeName);

}
