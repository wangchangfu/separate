package com.mapscience.modular.system.service;

import com.mapscience.modular.system.model.EmpPosition;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 公司部门岗位关系表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-03-05
 */
public interface IEmpPositionService extends IService<EmpPosition> {

    /**
     * 根据员工id查询empPosition
     *
     * @param employeeId
     * @return
     */
    List<EmpPosition> getEmpPositionByEmployeeId(String employeeId);

}
