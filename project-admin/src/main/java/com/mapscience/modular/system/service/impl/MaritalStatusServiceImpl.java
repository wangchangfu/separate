package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.model.MaritalStatus;
import com.mapscience.modular.system.mapper.MaritalStatusMapper;
import com.mapscience.modular.system.service.IMaritalStatusService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 婚姻状况表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@Service
public class MaritalStatusServiceImpl extends ServiceImpl<MaritalStatusMapper, MaritalStatus> implements IMaritalStatusService {


    @Autowired
    private MaritalStatusMapper maritalStatusMapper;


    @Override
    public MaritalStatus getMaritalStatusByName(String maritalStatusName) {
        return maritalStatusMapper.getMaritalStatusByName(maritalStatusName);
    }
}
