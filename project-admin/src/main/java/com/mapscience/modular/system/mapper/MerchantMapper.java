package com.mapscience.modular.system.mapper;

import com.mapscience.modular.system.model.Merchant;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 商户表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
public interface MerchantMapper extends BaseMapper<Merchant> {

    /**
     * 根据商户查找商户
     */
    Merchant getMerchant(Merchant merchant);
}
