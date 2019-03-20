package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.model.NationType;
import com.mapscience.modular.system.mapper.NationTypeMapper;
import com.mapscience.modular.system.service.INationTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 民族表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@Service
public class NationTypeServiceImpl extends ServiceImpl<NationTypeMapper, NationType> implements INationTypeService {

    @Autowired
    private NationTypeMapper nationTypeMapper;

    @Override
    public NationType getNationTypeByName(String nationStypeName) {
        return nationTypeMapper.getNationTypeByName(nationStypeName);
    }
}
