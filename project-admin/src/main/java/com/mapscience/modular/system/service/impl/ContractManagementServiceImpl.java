package com.mapscience.modular.system.service.impl;

import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.Company;
import com.mapscience.modular.system.model.ContractManagement;
import com.mapscience.modular.system.mapper.ContractManagementMapper;
import com.mapscience.modular.system.service.IContractManagementService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

    /**
     * 查询公司合同分布
     * @param company
     * @return
     */
    @Override
    public ResponseVal findContract(Company company) {
        return null;
    }
}
