package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.model.AccountType;
import com.mapscience.modular.system.mapper.AccountTypeMapper;
import com.mapscience.modular.system.service.IAccountTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 户口类别表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@Service
public class AccountTypeServiceImpl extends ServiceImpl<AccountTypeMapper, AccountType> implements IAccountTypeService {

    @Autowired
    private AccountTypeMapper accountTypeMapper;

    @Override
    public AccountType getAccountTypevByName(String accountTypeName) {
        return accountTypeMapper.getAccountTypevByName(accountTypeName);
    }
}
