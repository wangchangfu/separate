package com.mapscience.generator.engine.base;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.mapscience.generator.engine.config.*;

/**
 * 模板生成父类
 */
public abstract class AbstractTemplateEngine {

    protected ContextConfig contextConfig;                //全局配置
    protected ControllerConfig controllerConfig;          //控制器的配置
    protected MapperConfig mapperConfig;                        //Dao配置
    protected ServiceConfig serviceConfig;                //Service配置
    protected SqlConfig sqlConfig;                        //sql配置
    protected TableInfo tableInfo;                        //表的信息

    public void initConfig() {
        if (this.contextConfig == null) {
            this.contextConfig = new ContextConfig();
        }
        if (this.controllerConfig == null) {
            this.controllerConfig = new ControllerConfig();
        }
        if (this.mapperConfig == null) {
            this.mapperConfig = new MapperConfig();
        }
        if (this.serviceConfig == null) {
            this.serviceConfig = new ServiceConfig();
        }
        if (this.sqlConfig == null) {
            this.sqlConfig = new SqlConfig();
        }
        this.contextConfig.init();

        this.controllerConfig.setContextConfig(this.contextConfig);
        this.controllerConfig.init();

        this.serviceConfig.setContextConfig(this.contextConfig);
        this.serviceConfig.init();

        this.mapperConfig.setContextConfig(this.contextConfig);
        this.mapperConfig.init();


        this.sqlConfig.setContextConfig(this.contextConfig);
        this.sqlConfig.init();
    }

    public ContextConfig getContextConfig() {
        return contextConfig;
    }

    public void setContextConfig(ContextConfig contextConfig) {
        this.contextConfig = contextConfig;
    }

    public ControllerConfig getControllerConfig() {
        return controllerConfig;
    }

    public void setControllerConfig(ControllerConfig controllerConfig) {
        this.controllerConfig = controllerConfig;
    }

    public MapperConfig getMapperConfig() {
        return mapperConfig;
    }

    public void setMapperConfig(MapperConfig mapperConfig) {
        this.mapperConfig = mapperConfig;
    }

    public ServiceConfig getServiceConfig() {
        return serviceConfig;
    }

    public void setServiceConfig(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
    }

    public SqlConfig getSqlConfig() {
        return sqlConfig;
    }

    public void setSqlConfig(SqlConfig sqlConfig) {
        this.sqlConfig = sqlConfig;
    }

    public TableInfo getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(TableInfo tableInfo) {
        this.tableInfo = tableInfo;
    }

}
