package com.mapscience.modular.system.service;

import com.mapscience.modular.system.model.Merchant;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 商户表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
public interface IMerchantService extends IService<Merchant> {

    /**
     * 根据商户查找商户
     * @param merchant
     * @return
     */
    Merchant getMerchant(Merchant merchant);
}
