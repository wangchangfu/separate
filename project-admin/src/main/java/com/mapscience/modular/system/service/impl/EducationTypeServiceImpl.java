package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.model.EducationType;
import com.mapscience.modular.system.mapper.EducationTypeMapper;
import com.mapscience.modular.system.service.IEducationTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 员工学历字典表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@Service
public class EducationTypeServiceImpl extends ServiceImpl<EducationTypeMapper, EducationType> implements IEducationTypeService {

    @Autowired
    private EducationTypeMapper educationTypeMapper;

    @Override
    public EducationType getEducationTypeByEducationTypeName(String edcationTypeName) {
        return educationTypeMapper.getEducationTypeByEducationTypeName(edcationTypeName);
    }

    @Override
    public List<EducationType> getList() {
        return educationTypeMapper.getList();
    }
}
