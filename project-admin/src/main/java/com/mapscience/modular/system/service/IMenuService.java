package com.mapscience.modular.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.dto.MenuVueDTO;
import com.mapscience.modular.system.model.Menu;

import java.util.List;

/**
 * <p>
 * 菜单 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
public interface IMenuService extends IService<Menu> {


    /**
     * 递归查找菜单
     * @return
     */
    ResponseVal findmenuChildren();

    /**
     * 增加菜单
     * @param t
     */
    ResponseVal saveMenu(Menu t);

    /**
     * 获取当前用户的菜单
     * @param menuId
     * @return
     */
    List<Menu> findMenus(String menuId, String roleId);


    /**
     * 根据菜单Id查找下级菜单
     * @param m
     * @return
     */
    ResponseVal findChind(Menu m);

    /**
     * 查找下级菜单
     * @param menuId
     * @param roleId
     * @return
     */
    List<MenuVueDTO> findByIdMenuList(String menuId, String roleId);

    List<MenuVueDTO> findByMenuList(String menuId, String roleId);
}
