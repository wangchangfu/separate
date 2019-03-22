package com.mapscience.modular.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mapscience.modular.system.model.Health;

import java.util.List;

/**
 * <p>
 * 健康状况表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
public interface HealthMapper extends BaseMapper<Health> {

    List<Health> getList();

    Health getHealthByName(String healthName);

    Health getHealthById(String healthId);

}
