package com.mapscience.modular.system.service.impl;

import java.util.List;

import com.mapscience.core.common.ResponseVal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mapscience.modular.system.mapper.WorkHistoryMapper;
import com.mapscience.modular.system.model.WorkHistory;
import com.mapscience.modular.system.service.IWorkHistoryService;

/**
 * <p>
 * 工作经验表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@Service
public class WorkHistoryServiceImpl extends ServiceImpl<WorkHistoryMapper, WorkHistory> implements IWorkHistoryService {
	
	@Autowired
    private WorkHistoryMapper workHistoryMapper;

	@Override
	public List<WorkHistory> getWorkHistoryByEmpId(String empId) {
		return workHistoryMapper.getWorkHistoryByEmpId(empId);
	}

	@Override
	public ResponseVal saveWorkHistory(WorkHistory workHistory) {
		try {

			this.baseMapper.saveWorkHistory(workHistory);

			return new ResponseVal(200, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVal(500,"保存错误",e);
		}
	}

}
