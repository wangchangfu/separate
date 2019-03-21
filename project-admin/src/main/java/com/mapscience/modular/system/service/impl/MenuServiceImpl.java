package com.mapscience.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.mapper.MenuMapper;
import com.mapscience.modular.system.model.Menu;
import com.mapscience.modular.system.service.IMenuService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    /**
     * 递归查询菜单
     * @return
     */
    @Override
    public ResponseVal findmenuChildren(){
        try{
            /**
             * 查询出所有
             */
            List<Menu> menus = this.baseMapper.findmenuChildren();
            /**
             * 将List转为tree
             */
            List<Menu> bulid = bulid(menus);
            //将bulid存入redis缓存
           // JedisUtil.setObject("menuTree", bulid);
            return new ResponseVal(200,"查询成功",bulid);
        }catch (Exception e ){
            e.printStackTrace();
            return new ResponseVal(500,"查询出错");
        }

    }

    /**
     * 递归查找菜单
     * @param treeNodes
     * @return
     */
    public List<Menu> bulid(List<Menu> treeNodes){
        List<Menu> trees= new ArrayList<Menu>();
        for (Menu t:treeNodes){
            if("0".equals(t.getParentId())){
                trees.add(findChildre(t,treeNodes));
            }
        }
        return trees;
    }

    /**
     * 内部调用查找下级菜单方法
     * @param t
     * @param treeNodes
     * @return
     */
    private Menu findChildre(Menu t, List<Menu> treeNodes) {
        t.setChildren(new ArrayList<Menu>());
        for (Menu me: treeNodes){
            if(t.getMenuId().equals(me.getParentId())){
                if(t.getChildren()==null){
                    t.setChildren(new ArrayList<Menu>());
                }
                t.getChildren().add(findChildre(me,treeNodes));
            }
        }
        return t;
    }

    /**
     * 添加菜单
     */
    @Override
    public ResponseVal saveMenu(Menu t) {
        try{
            this.baseMapper.saveMenu(t);
            String menuId = t.getMenuId();
            return new ResponseVal(200,"保存成功",menuId);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseVal(500,"数据保存出错");
        }

    }

    /**
     * 根据菜单ID查询当前用户菜单
     * @return
     */
    @Override
    public List<Menu> findMenus(String menuId, String roleId) {
        List<Menu> menus = this.baseMapper.findMenus(menuId,roleId);
        for (Menu m: menus) {
            List<Menu> menus1 = this.baseMapper.findMenus(m.getMenuId(),roleId);
            m.setChildren(menus1);
        }
        return menus;
    }

    /**
     * 根据菜单id查询菜单
     * @param m
     * @return
     */
    @Override
    public ResponseVal findChind(Menu m) {
        List<Menu> menus = this.baseMapper.findChind(m.getMenuId());
        for (Menu me: menus) {

            List<Menu> menus1 = this.baseMapper.findChind(me.getMenuId());
            m.setChildren(menus1);
        }
        return new ResponseVal("查询成功",menus);
    }


}
