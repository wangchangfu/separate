package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.model.Company;
import com.mapscience.modular.system.mapper.CompanyMapper;
import com.mapscience.modular.system.service.ICompanyService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公司基本信息表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {

}
