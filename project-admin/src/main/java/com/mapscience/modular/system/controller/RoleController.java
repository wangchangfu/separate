package com.mapscience.modular.system.controller;


import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.Role;
import com.mapscience.modular.system.model.RolePermission;
import com.mapscience.modular.system.service.IRolePermissionService;
import com.mapscience.modular.system.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-03-05
 */
@Api(tags="角色")
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;


    @Autowired
    private IRolePermissionService rolePermissionService;
    /**
     * 添加角色
     * @return
     */
    @ApiOperation(value = "添加角色")
    @PostMapping("addRole")
    @ResponseBody
    public ResponseVal<Role> addRole(@RequestBody Role rule){
        return  this.roleService.addRole(rule);
    }


    /**
     * 根据角色分配菜单  传参角色ID，菜单以逗号分隔
     * @return
     */
    @ApiOperation(value = "根据角色分配菜单")
    @RequestMapping(value = "distrMenu",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVal distrMenu(@RequestBody RolePermission m){
        ResponseVal responseVal = this.rolePermissionService.distrMenu(m.getRoleId(), m.getMenuId());
        return responseVal;

    }

    /**
     * 查询所有的角色
     * @return
     */
    @ApiOperation(value = "查询角色")
    @RequestMapping(value = "findRoleList" , method = RequestMethod.POST)
    public ResponseVal<Role> findRoleList(){
        ResponseVal roleList = this.roleService.findRoleList();
        return roleList;
    }
}

