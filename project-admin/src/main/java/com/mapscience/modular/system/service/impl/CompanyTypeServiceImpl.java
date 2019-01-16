package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.model.CompanyType;
import com.mapscience.modular.system.mapper.CompanyTypeMapper;
import com.mapscience.modular.system.service.ICompanyTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公司业务类型表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@Service
public class CompanyTypeServiceImpl extends ServiceImpl<CompanyTypeMapper, CompanyType> implements ICompanyTypeService {

}
