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
	public ArrayList<HashMap<String, String>> findContractByCompanyId(String companyId, String contractType, int numberOfYearAgo) {
		ArrayList<HashMap<String, String>> newArrayListForReturn = Lists.newArrayList();
		String[] contractTypeArr = contractType.split(",");
		HashMap<String, String> params = Maps.newHashMap();
		params.put("signingComId", companyId);
		int nowYear = LocalDate.now().getYear();
		for(int i=1;i<=numberOfYearAgo;i++) {
			HashMap<String, String> newHashMap = Maps.newHashMap();
			newHashMap.put("年份", nowYear-i+"");
			for (String string : contractTypeArr) {
				params.put("contractType", string);
				params.put("year", nowYear-i+"");
				newHashMap.put(string, mapper.findContract(params).size()+"");
			}
			newArrayListForReturn.add(newHashMap);
		}
		return newArrayListForReturn;
	}
	
}
