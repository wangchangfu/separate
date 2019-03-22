package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.model.EmpPosition;
import com.mapscience.modular.system.mapper.EmpPositionMapper;
import com.mapscience.modular.system.service.IEmpPositionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 公司部门岗位关系表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-03-05
 */
@Service
public class EmpPositionServiceImpl extends ServiceImpl<EmpPositionMapper, EmpPosition> implements IEmpPositionService {


    /**
     * 根据员工id查询empPosition
     *
     * @param employeeId
     * @return
     */
    @Override
    public List<EmpPosition> getEmpPositionByEmployeeId(String employeeId) {
        return this.baseMapper.getEmpPositionByEmployeeId(employeeId);
    }
}
