package com.mapscience.modular.system.mapper;

import com.mapscience.modular.system.model.NationalityType;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 国籍表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
public interface NationalityTypeMapper extends BaseMapper<NationalityType> {


    NationalityType getnationalityTypeByName(String nationalityStypeName);

}
