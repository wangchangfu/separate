package com.mapscience.modular.system.mapper;

import com.mapscience.modular.system.model.CompanyType;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 公司业务类型表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
public interface CompanyTypeMapper extends BaseMapper<CompanyType> {

    @Select("select * from t_company_type ")
    List<CompanyType> findComType();
}
