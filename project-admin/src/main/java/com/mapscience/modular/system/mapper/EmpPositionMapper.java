package com.mapscience.modular.system.mapper;

import com.mapscience.modular.system.model.EmpPosition;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 公司部门岗位关系表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-03-05
 */
public interface EmpPositionMapper extends BaseMapper<EmpPosition> {

    /**
     * 根据员工id查询empPosition
     *
     * @param employeeId
     * @return
     */
    List<EmpPosition> getEmpPositionByEmployeeId(String employeeId);

}
