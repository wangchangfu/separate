package com.mapscience.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.util.ObjectUtil;
import com.mapscience.modular.system.mapper.RoleMapper;
import com.mapscience.modular.system.model.Role;
import com.mapscience.modular.system.service.IRoleService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
            rule.setStatus(1);
            rule.setCreateTime(new Date());
            rule.setUpdateTime(new Date());
            this.baseMapper.addRole(rule);
            String roleId = rule.getRoleId();
            return new ResponseVal("添加成功",roleId);
        }catch (Exception e){
            return new ResponseVal(HttpStatus.INTERNAL_SERVER_ERROR.value(),"保存数据错误",e.getMessage());
        }
    }

    @Override
    public Role findByRoleId(String userId) {
        return this.baseMapper.findByRoleId(userId);
    }

    /**
     * 查询所有的角色
     * @return
     */
    @Override
    public ResponseVal findRoleList() {
        try{
            List<Role> roleList = this.baseMapper.findRoleList();
            if (ObjectUtil.isEmpty(roleList)){
                return new ResponseVal(HttpStatus.FOUND.value(),"暂无数据");
            }
            return new ResponseVal("查询成功",roleList);
        }catch (Exception e){
            return new ResponseVal(HttpStatus.INTERNAL_SERVER_ERROR.value(),"",e.getMessage());
        }

    }


}
