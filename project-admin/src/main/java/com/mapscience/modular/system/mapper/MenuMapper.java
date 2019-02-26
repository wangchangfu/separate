package com.mapscience.modular.system.mapper;

import com.mapscience.modular.system.model.Menu;
import com.baomidou.mybatisplus.mapper.BaseMapper;

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

    void saveMenu(Menu t);
}
