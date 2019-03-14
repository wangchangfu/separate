package com.mapscience.modular.system.mapper;

import com.mapscience.modular.system.model.Menu;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 菜单 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 递归查询所有菜单
     * @return
     */
    List<Menu> findmenuChildren();

    /**
     * 保存菜单
     * @param t
     */
    void saveMenu(Menu t);

    /**
     * 根据当前的菜单ID查询用户的菜单
     * @param menuId
     * @param employeeId
     * @return
     */
    @Select("")
    List<Menu> findMenus(String menuId, String employeeId);
}
