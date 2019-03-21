package com.mapscience.modular.system.mapper;

import com.mapscience.modular.system.model.MaritalStatus;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 婚姻状况表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
public interface MaritalStatusMapper extends BaseMapper<MaritalStatus> {


    MaritalStatus getMaritalStatusByName(String maritalStatusName);

}
