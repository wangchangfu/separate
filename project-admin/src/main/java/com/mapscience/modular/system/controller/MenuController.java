package com.mapscience.modular.system.controller;


import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.util.JedisUtil;
import com.mapscience.modular.system.model.Menu;
import com.mapscience.modular.system.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 菜单 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    /**
     * 查询菜单树
     * @return
     */
    @RequestMapping("menuTree")
    @ResponseBody
    public ResponseVal menuTree(){
        JedisUtil.getObject("menuTree");
        return this.menuService.findmenuChildren();
    }



    /**
     * 菜单添加
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "saveMenu",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseVal saveMenu(@RequestBody Menu t){

        return  this.menuService.saveMenu(t);
    }
}

