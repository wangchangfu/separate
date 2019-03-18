package com.mapscience.modular.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mapscience.modular.system.model.Menu;
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
     * @return
     */

    @Select("select * from t_menu where status=1 and parent_id=#{menuId}")
    List<Menu> findMenus(String parentId);


    List<String> getResUrlsByRoleId(String roleId);

    @Select("select * from t_menu where status=1 and parent_id=#{menuId}")
    List<Menu> findChind(Menu m);
}
