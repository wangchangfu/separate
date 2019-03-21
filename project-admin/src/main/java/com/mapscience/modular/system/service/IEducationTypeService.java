package com.mapscience.modular.system.service;

import com.mapscience.modular.system.model.EducationType;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 员工学历字典表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
public interface IEducationTypeService extends IService<EducationType> {

    EducationType getEducationTypeByEducationTypeName(String EdcationTypeName);

    List<EducationType> getList();

}
