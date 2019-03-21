package com.mapscience.modular.system.mapper;

import com.mapscience.modular.system.model.AccountType;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 户口类别表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
public interface AccountTypeMapper extends BaseMapper<AccountType> {

    AccountType getAccountTypevByName(String accountTypeName);

}
