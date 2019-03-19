package com.mapscience.modular.system.service;

import com.mapscience.modular.system.model.PoliticalStatus;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 政治面貌表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
public interface IPoliticalStatusService extends IService<PoliticalStatus> {

    PoliticalStatus getPoliticalStatusByName(String politicalStatusName);

}
