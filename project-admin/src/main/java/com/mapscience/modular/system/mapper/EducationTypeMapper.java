package com.mapscience.modular.system.mapper;

import com.mapscience.modular.system.model.EducationType;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 员工学历字典表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
public interface EducationTypeMapper extends BaseMapper<EducationType> {

    EducationType getEducationTypeByEducationTypeName(String educationTypeName);

    List<EducationType> getList();

}
