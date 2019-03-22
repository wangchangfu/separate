package com.mapscience.modular.system.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mapscience.modular.system.model.Education;
import com.mapscience.modular.system.model.Employee;

/**
 * <p>
 * 教育信息表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
public interface EducationMapper extends BaseMapper<Education> {
	
	List<Education> findEducation(@Param("params")HashMap<String, String> params);


	List<Education> getList();

}
