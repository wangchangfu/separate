package com.mapscience.modular.code.factory;

import com.mapscience.ProjectGeneratorApplication;
import com.mapscience.core.CoreFlag;
import com.mapscience.core.util.ToolUtil;
import com.mapscience.generator.action.model.CodeProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模板种类构建器
 */
public class DefaultTemplateFactory {
    /**
     * 获取所有的模板种类
     */
    public static List<Map<String,Object>> getDefaultTemplates(){
        ArrayList<Map<String, Object>> templates = new ArrayList<>();
        templates.add(create("controllerSwitch","controller-控制器模板"));
        templates.add(create("entitySwitch","entity-实体模板"));
        templates.add(create("serviceSwitch","service-service模板"));
        templates.add(create("daoSwitch","dao-dao模板"));
        //templates.add(create("indexPageSwitch","indexPage-首页模板"));
        //templates.add(create("addPageSwitch","addPage-添加页面模板"));
        //templates.add(create("editPageSwitch","editPage-编辑页面模板"));
        //templates.add(create("jsSwitch","indexJs-主页js模板"));
        //templates.add(create("infoJsSwitch","infoJs-详情页js模板"));
        //templates.add(create("sqlSwitch","sql-sql语句模板"));
        return templates;
    }

    public static CodeProperties getDefaultParams(){
        CodeProperties codeProperties=new CodeProperties();
        codeProperties.setProjectPath(ToolUtil.getWebRootPath("project-admin"));
        codeProperties.setAuthor("tyj");
        codeProperties.setProjectPackage(ProjectGeneratorApplication.class.getPackage().getName());
        codeProperties.setCorePackage(CoreFlag.class.getPackage().getName());
        codeProperties.setIgnoreTabelPrefix("sys_");
        codeProperties.setModuleName("system");
        codeProperties.setParentMenuName("系统管理");
        return codeProperties;
    }

    private static Map<String,Object> create(String key,String desc){
        HashMap<String, Object> template = new HashMap<>();
        template.put("key",key);
        template.put("desc",desc);
        return template;
    }
}
