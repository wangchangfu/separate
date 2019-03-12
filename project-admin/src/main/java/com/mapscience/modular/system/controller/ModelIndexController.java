package com.mapscience.modular.system.controller;

import com.google.common.collect.Maps;
import com.mapscience.core.base.controller.BaseController;
import com.mapscience.modular.system.model.Employee;
import com.mapscience.modular.system.model.Menu;
import com.mapscience.modular.system.service.IMenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class ModelIndexController extends BaseController {


    /**
     * 默认路径
     */
    private final String PREFIX = "/modular/";
    /**
     *
     */
    @Autowired
    private IMenuService menuService;


    /**
     * 返回菜单的路径
     * @param menu
     * @return
     */
    @RequestMapping("modelIndex")
    @ResponseBody
    public ModelAndView modelIndex(Menu menu) {
        Map<String, Object> params = Maps.newHashMap();
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Employee employee = (Employee)session.getAttribute("emp");


        //查询管理员所在公司
        /*List<Company> comByEmp = this.companyService.findComByEmp("0");
        if (comByEmp==null){
            Company comById = this.companyService.findComById("1");
            params.put("organize", comById);
        }else{
            params.put("organize", comByEmp);
        }
        //获取菜单
        //List<Menu> menus= this.menuService.findMenuById(menu, employee);
        //获取当前用户的菜单
        List<Menu> menus = this.menuService.findMenus(menu);
        params.put("menus",menus);    //菜单
        params.put("manager",employee);//管理员
        params.put("menuId",menu.getMenuId());*/
        return new ModelAndView(PREFIX+"modelIndex", params);
    }
}
