package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.model.Position;
import com.mapscience.modular.system.mapper.PositionMapper;
import com.mapscience.modular.system.service.IPositionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 职位表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements IPositionService {

}
