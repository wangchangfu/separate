package com.mapscience.generator.action.config;

import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.mapscience.core.support.StrKit;
import com.mapscience.core.util.ToolUtil;
import com.mapscience.generator.action.model.CodeProperties;

import java.io.File;

public class WebGeneratorConfig extends AbstractGeneratorConfig {

    private CodeProperties codeProperties;

    public WebGeneratorConfig(CodeProperties codeProperties) {
        this.codeProperties = codeProperties;
    }


    @Override
    protected void config() {
        /**
         * 数据库配置
         */
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername(codeProperties.getUserName());
        dataSourceConfig.setPassword(codeProperties.getPassword());
        dataSourceConfig.setUrl(codeProperties.getUrl());

        /**
         * 全局配置
         */
        globalConfig.setOutputDir(codeProperties.getProjectPath() + File.separator + "src" + File.separator + "main" + File.separator + "java");
        globalConfig.setFileOverride(true);
        globalConfig.setEnableCache(false);
        globalConfig.setBaseResultMap(true);
        globalConfig.setBaseColumnList(true);
        globalConfig.setOpen(false);
        globalConfig.setAuthor(codeProperties.getAuthor());
        contextConfig.setProPackage(codeProperties.getProjectPackage());
        contextConfig.setCoreBasePackage(codeProperties.getCorePackage());

        /**
         * 生成策略
         */
        if (codeProperties.getIgnoreTabelPrefix() != null) {
            strategyConfig.setTablePrefix(new String[]{codeProperties.getIgnoreTabelPrefix()});
        }
        strategyConfig.setInclude(new String[]{codeProperties.getTableName()});
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        packageConfig.setParent(null);
        packageConfig.setEntity(codeProperties.getProjectPackage() + ".modular.system.model");
        packageConfig.setMapper(codeProperties.getProjectPackage() + ".modular.system.mapper");
        packageConfig.setXml(codeProperties.getProjectPackage() + ".modular.system.mapper.mappers");


        /**
         * 业务代码配置
         */
        contextConfig.setBizChName(codeProperties.getBizName());
        contextConfig.setModuleName(codeProperties.getModuleName());
        contextConfig.setProjectPath(codeProperties.getProjectPath());//写自己项目的绝对路径

        if(ToolUtil.isEmpty(codeProperties.getIgnoreTabelPrefix())){
            String entityName = StrKit.toCamelCase(codeProperties.getTableName());
            contextConfig.setEntityName(StrKit.firstCharToUpperCase(entityName));
            contextConfig.setBizEnName(StrKit.firstCharToLowerCase(entityName));
        }else{
            String entiyName = StrKit.toCamelCase(StrKit.removePrefix(codeProperties.getTableName(), codeProperties.getIgnoreTabelPrefix()));
            contextConfig.setEntityName(StrKit.firstCharToUpperCase(entiyName));
            contextConfig.setBizEnName(StrKit.firstCharToLowerCase(entiyName));
        }
        sqlConfig.setParentMenuName(codeProperties.getParentMenuName());//这里写已有菜单的名称,当做父节点


        /**
         * mybatis-plus 生成器开关
         */
        contextConfig.setEntitySwitch(codeProperties.getEntitySwitch());
        contextConfig.setDaoSwitch(codeProperties.getDaoSwitch());
        contextConfig.setServiceSwitch(codeProperties.getServiceSwitch());


        /**
         *  生成器开关
         */
        contextConfig.setControllerSwitch(codeProperties.getControllerSwitch());
        contextConfig.setIndexPageSwitch(codeProperties.getIndexPageSwitch());
        contextConfig.setAddPageSwitch(codeProperties.getAddPageSwitch());
        contextConfig.setEditPageSwitch(codeProperties.getEditPageSwitch());
        contextConfig.setJsSwitch(codeProperties.getJsSwitch());
        contextConfig.setInfoJsSwitch(codeProperties.getInfoJsSwitch());
        contextConfig.setSqlSwitch(codeProperties.getSqlSwitch());
    }
}
