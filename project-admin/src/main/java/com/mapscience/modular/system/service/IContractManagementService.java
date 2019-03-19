package com.mapscience.modular.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.Company;
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
     * @param company
     * @return
     */
    ResponseVal findContract(Company company);
}
