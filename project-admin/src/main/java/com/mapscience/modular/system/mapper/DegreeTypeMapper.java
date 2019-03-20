package com.mapscience.modular.system.mapper;

import com.mapscience.modular.system.model.DegreeType;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 员工学位字典表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
public interface DegreeTypeMapper extends BaseMapper<DegreeType> {

    DegreeType getDegreeByDegreeName(String degreeName);

}
