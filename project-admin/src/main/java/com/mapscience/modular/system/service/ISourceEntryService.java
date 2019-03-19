package com.mapscience.modular.system.service;

import com.mapscience.modular.system.model.SourceEntry;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 进入公司途径来源表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
public interface ISourceEntryService extends IService<SourceEntry> {

    SourceEntry getSourceEntryByName(String sourceEntryName);


}
