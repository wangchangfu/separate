package com.mapscience.modular.system.mapper;

import com.mapscience.modular.system.model.WorkHistory;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 工作经验表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
public interface WorkHistoryMapper extends BaseMapper<WorkHistory> {
	
	List<WorkHistory> getWorkHistoryByEmpId(String empId);

	void saveWorkHistory(WorkHistory workHistory);


}
