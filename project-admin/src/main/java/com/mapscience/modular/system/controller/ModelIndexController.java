package com.mapscience.modular.system.controller;

import com.google.common.collect.Maps;
import com.mapscience.core.base.controller.BaseController;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.Company;
import com.mapscience.modular.system.model.Employee;
import com.mapscience.modular.system.model.Menu;
import com.mapscience.modular.system.model.UserRole;
import com.mapscience.modular.system.service.ICompanyService;
import com.mapscience.modular.system.service.IMenuService;
import com.mapscience.modular.system.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class ModelIndexController extends BaseController {


    /**
     * 默认路径
     */
    private final String PREFIX = "/modular/";
    /**
     *菜单
     */
    @Autowired
    private IMenuService menuService;

    /**
     * 角色
     */

    @Autowired
    private IUserRoleService userRoleService;
    /**
     * 公司
     */
    @Autowired
    private ICompanyService companyService;
    /**
     * 返回菜单的路径
     * @param menu
     * @return
     */
    @RequestMapping("modelIndex")
    @ResponseBody
    public ResponseVal modelIndex(@RequestBody Menu menu, @RequestBody Employee employee) {
        Map<String, Object> params = Maps.newHashMap();
        //查询管理员所在公司
        List<Company> comByEmp = this.companyService.findComByEmp(employee.getEmployeeId());
        if (comByEmp==null){
            Company comById = this.companyService.findComById("1");
            params.put("organize", comById);
        }else{
            params.put("organize", comByEmp);
        }
        //获取菜单
        //List<Menu> menus= this.menuService.findMenuById(menu, employee);
        //获取当前用户的菜单
        //查询角色ID
        UserRole byEmp = this.userRoleService.findByEmp(employee.getEmployeeId());
        List<Menu> menus = this.menuService.findMenus(menu,byEmp.getRoleId());
        params.put("menus",menus);    //菜单
        params.put("manager",employee);//管理员
        params.put("menuId",menu.getMenuId());
        return new ResponseVal(HttpStatus.OK.value(),"查找成功",params);
    }
}
