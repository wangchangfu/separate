package com.mapscience.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mapscience.modular.system.mapper.HealthMapper;
import com.mapscience.modular.system.model.Health;
import com.mapscience.modular.system.service.IHealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 健康状况表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@Service
public class HealthServiceImpl extends ServiceImpl<HealthMapper, Health> implements IHealthService {


    @Autowired
    private HealthMapper healthMapper;

    @Override
    public List<Health> getList() {

        return this.baseMapper.getList();
    }

    @Override
    public Health getHealth(String healthName) {
        return healthMapper.getHealth(healthName);
    }
}
