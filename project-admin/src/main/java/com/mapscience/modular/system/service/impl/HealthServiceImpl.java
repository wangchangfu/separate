package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.model.Health;
import com.mapscience.modular.system.mapper.HealthMapper;
import com.mapscience.modular.system.service.IHealthService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
