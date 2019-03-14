package com.mapscience.modular.system.mapper;

import com.mapscience.modular.system.model.Education;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 教育信息表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
public interface EducationMapper extends BaseMapper<Education> {
	
	Education getHighestDegreeByEmpId(String empId);

}
