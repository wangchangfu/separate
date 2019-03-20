package com.mapscience.modular.system.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.baomidou.mybatisplus.service.IService;
import com.mapscience.modular.system.model.ContractManagement;

/**
 * <p>
 * 合同表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
public interface IContractManagementService extends IService<ContractManagement> {

    /**
     * 根据公司ID查询员工合同分布
     * @param companyId	公司id
     * @param contractType 合同类型字符串 如："固定期,无固定,实习协议,劳务合同,其他"
     * @param numberOfYearAgo 多少年前 
     * @return
     */
	ArrayList<HashMap<String, Integer>> findContractByCompanyId(String companyId, String contractType, int numberOfYearAgo);
}
