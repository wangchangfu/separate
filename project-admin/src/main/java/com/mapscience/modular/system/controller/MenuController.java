package com.mapscience.modular.system.controller;


import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.shiro.ShiroKit;
import com.mapscience.core.shiro.ShiroUser;
import com.mapscience.core.util.JedisUtil;
import com.mapscience.modular.system.model.Menu;
import com.mapscience.modular.system.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 菜单 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@Api(tags="菜单")
@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    /**
     * 查询菜单树
     * @return
     */
    @ApiOperation(value = "查询菜单树")
    @RequestMapping("menuTree")
    @ResponseBody
    public ResponseVal menuTree(){
        //JedisUtil.getObject("menuTree");
        //获取当前用户
            ShiroUser shiroUser = ShiroKit.getUser();

            String menuTree = JedisUtil.getJson("menuTree");
            if (menuTree==null) {
                return this.menuService.findmenuChildren();
            }
            return new ResponseVal(0,"",menuTree);
    }



    /**
     * 菜单添加
     * @return
     */
    @ApiOperation(value = "菜单添加")
    @ResponseBody
    @RequestMapping(value = "saveMenu",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseVal saveMenu(@RequestBody Menu t){

        return  this.menuService.saveMenu(t);
    }


}

