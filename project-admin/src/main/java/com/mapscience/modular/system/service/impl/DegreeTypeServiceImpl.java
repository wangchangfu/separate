package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.mapper.DepartmentMapper;
import com.mapscience.modular.system.model.DegreeType;
import com.mapscience.modular.system.mapper.DegreeTypeMapper;
import com.mapscience.modular.system.service.IDegreeTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工学位字典表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@Service
public class DegreeTypeServiceImpl extends ServiceImpl<DegreeTypeMapper, DegreeType> implements IDegreeTypeService {



    @Override
    public DegreeType getDegreeByDegreeName(String degreeName) {
        return this.baseMapper.getDegreeByDegreeName(degreeName);
    }
}
