package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.model.LoginLog;
import com.mapscience.modular.system.mapper.LoginLogMapper;
import com.mapscience.modular.system.service.ILoginLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 登录记录 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements ILoginLogService {

}
