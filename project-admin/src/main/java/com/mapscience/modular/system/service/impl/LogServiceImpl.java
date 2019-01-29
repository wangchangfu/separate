package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.model.Log;
import com.mapscience.modular.system.mapper.LogMapper;
import com.mapscience.modular.system.service.ILogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 重要操作日志数据库 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}
