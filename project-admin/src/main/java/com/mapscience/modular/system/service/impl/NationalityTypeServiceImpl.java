package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.model.NationalityType;
import com.mapscience.modular.system.mapper.NationalityTypeMapper;
import com.mapscience.modular.system.service.INationalityTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 国籍表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@Service
public class NationalityTypeServiceImpl extends ServiceImpl<NationalityTypeMapper, NationalityType> implements INationalityTypeService {


    @Autowired
    private NationalityTypeMapper nationalityTypeMapper;

    @Override
    public NationalityType getnationalityTypeByName(String nationalityStypeName) {
        return nationalityTypeMapper.getnationalityTypeByName(nationalityStypeName);
    }
}
