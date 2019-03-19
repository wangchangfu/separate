package com.mapscience.modular.system.service;

import com.mapscience.modular.system.model.DegreeType;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 员工学位字典表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
public interface IDegreeTypeService extends IService<DegreeType> {

    DegreeType getDegreeByDegreeName(String degreeName);

}
