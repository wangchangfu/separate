package com.mapscience.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.mapper.RolePermissionMapper;
import com.mapscience.modular.system.model.RolePermission;
import com.mapscience.modular.system.service.IRolePermissionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 角色资源表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-03-05
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements IRolePermissionService {

    /**
     * 分配菜单
     * @param ruleId
     * @return
     */
    @Override
    public ResponseVal distrMenu(String ruleId, String m) {
        try{
            String [] menuId=m.split(",");
            for (int i=0; i< menuId.length;i++){
                RolePermission p = new RolePermission();
                p.setCreateTime(new Date());
                p.setMenuId(menuId[i]);
                p.setUpdateTime(new Date());
                p.setRoleId(ruleId);
                this.baseMapper.distrMenu(p);
            }
            return new ResponseVal(0,"分配成功");
        }catch (Exception e ){
        return new ResponseVal(HttpStatus.INTERNAL_SERVER_ERROR.value(),"分配菜单错误",e.getMessage());
        }
    }
}
