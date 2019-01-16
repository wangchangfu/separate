package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.model.OperationLog;
import com.mapscience.modular.system.mapper.OperationLogMapper;
import com.mapscience.modular.system.service.IOperationLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements IOperationLogService {

}
