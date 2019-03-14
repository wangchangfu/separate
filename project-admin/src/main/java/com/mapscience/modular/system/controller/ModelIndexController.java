package com.mapscience.modular.system.controller;

import com.mapscience.core.base.controller.BaseController;
import com.mapscience.modular.system.service.ICompanyService;
import com.mapscience.modular.system.service.IMenuService;
import com.mapscience.modular.system.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 大屏
 */
@Controller
@RequestMapping("/ModelIndex")
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

}
