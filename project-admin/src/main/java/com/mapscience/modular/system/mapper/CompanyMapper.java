package com.mapscience.modular.system.mapper;

import com.mapscience.modular.system.model.Company;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 公司基本信息表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
public interface CompanyMapper extends BaseMapper<Company> {

    /**
     * 查找公司
     * @return
     */
    List<Company> getList();

    /**
     * 保存
     * @param company
     * @return
     */
    String saveCompany(Company company);
}
