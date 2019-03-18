package com.mapscience.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.mapper.RoleMapper;
import com.mapscience.modular.system.model.Role;
import com.mapscience.modular.system.service.IRoleService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-03-05
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    /**
     * 添加角色
     * @param rule
     * @return
     */
    @Override
    public ResponseVal addRole(Role rule) {
        try{
            rule.setCreateTime(new Date());
            rule.setUpdateTime(new Date());
            this.baseMapper.addRole(rule);
            String roleId = rule.getRoleId();
            return new ResponseVal("添加成功",roleId);
        }catch (Exception e){
            return new ResponseVal(HttpStatus.INTERNAL_SERVER_ERROR.value(),"保存数据错误",e.getMessage());
        }
    }

    /**
     * 分配菜单
     * @param ruleId
     * @param menuId
     */
    @Override
    public void distrMenu(String ruleId, String menuId) {

    }


}
