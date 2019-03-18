package com.mapscience.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.util.ObjectUtil;
import com.mapscience.modular.system.mapper.CompanyTypeMapper;
import com.mapscience.modular.system.model.CompanyType;
import com.mapscience.modular.system.service.ICompanyTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 公司业务类型表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@Service
public class CompanyTypeServiceImpl extends ServiceImpl<CompanyTypeMapper, CompanyType> implements ICompanyTypeService {

    /**
     * 查询公司行业分类
     * @return
     */
    @Override
    public ResponseVal findComType() {
        List<CompanyType> comType = this.baseMapper.findComType();
            if (ObjectUtil.isNotEmpty(comType)){
                return new ResponseVal(200, "查询成功",comType);
            }
        return new ResponseVal(HttpStatus.FOUND.value(),"暂无数据");
    }
}
