package com.mapscience.modular.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.mapscience.modular.system.model.Health;

import java.util.List;

/**
 * <p>
 * 健康状况表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
public interface IHealthService extends IService<Health> {

    List<Health> getList();

    Health getHealth(String healthName);

}
