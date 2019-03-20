package com.mapscience.modular.system.service;

import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.WorkHistory;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 工作经验表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
public interface IWorkHistoryService extends IService<WorkHistory> {
	
	List<WorkHistory> getWorkHistoryByEmpId(String empId);

	ResponseVal saveWorkHistory(WorkHistory workHistory);

}
