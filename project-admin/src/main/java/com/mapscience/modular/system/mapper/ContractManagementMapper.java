package com.mapscience.modular.system.mapper;

import com.mapscience.modular.system.model.ContractManagement;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 合同表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
public interface ContractManagementMapper extends BaseMapper<ContractManagement> {
	
	List<ContractManagement> findContract(@Param("params")HashMap<String, String> params);

}
