package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.model.CredentialsType;
import com.mapscience.modular.system.mapper.CredentialsTypeMapper;
import com.mapscience.modular.system.service.ICredentialsTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 证件类型表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@Service
@Transactional
public class CredentialsTypeServiceImpl extends ServiceImpl<CredentialsTypeMapper, CredentialsType> implements ICredentialsTypeService {

}
