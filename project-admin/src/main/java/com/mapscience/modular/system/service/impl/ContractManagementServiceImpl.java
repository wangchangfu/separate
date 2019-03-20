package com.mapscience.modular.system.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mapscience.modular.system.mapper.ContractManagementMapper;
import com.mapscience.modular.system.model.ContractManagement;
import com.mapscience.modular.system.service.IContractManagementService;

/**
 * <p>
 * 合同表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@Service
public class ContractManagementServiceImpl extends ServiceImpl<ContractManagementMapper, ContractManagement> implements IContractManagementService {

	@Autowired
    private ContractManagementMapper mapper;
	
	@Override
	public ArrayList<HashMap<String, Integer>> findContractByCompanyId(String companyId, String contractType, int numberOfYearAgo) {
		String[] contractTypeArr = contractType.split(",");
		ContractManagement contractManagement = new ContractManagement();
		contractManagement.setSigningComId(companyId);
		int nowYear = LocalDate.now().getYear();
		ArrayList<HashMap<String, Integer>> newArrayListForReturn = Lists.newArrayList();
		for(int i=1;i<=numberOfYearAgo;i++) {
			HashMap<String, Integer> newHashMap = Maps.newHashMap();
			newHashMap.put("年份", nowYear-i);
			for (String string : contractTypeArr) {
				contractManagement.setContractType(string);
				newHashMap.put(string, mapper.findContract(contractManagement, nowYear-i).size());
			}
			newArrayListForReturn.add(newHashMap);
		}
		return newArrayListForReturn;
	}
	
}
