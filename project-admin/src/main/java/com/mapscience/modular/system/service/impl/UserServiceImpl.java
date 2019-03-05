package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.model.User;
import com.mapscience.modular.system.mapper.UserMapper;
import com.mapscience.modular.system.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-03-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
