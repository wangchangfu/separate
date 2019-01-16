package com.mapscience.core.datascope;

import java.util.List;

/**
 * 数据范围
 *
 */
public class DataScope {

    /**
     * 限制范围的字段名称
     */
    private String scopeName = "deptid";

    /**
     * 具体的数据范围
     */
    private List<String> deptIds;

    public DataScope() {
    }

    public DataScope(List<String> deptIds) {
        this.deptIds = deptIds;
    }

    public DataScope(String scopeName, List<String> deptIds) {
        this.scopeName = scopeName;
        this.deptIds = deptIds;
    }

    public List<String> getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(List<String> deptIds) {
        this.deptIds = deptIds;
    }

    public String getScopeName() {
        return scopeName;
    }

    public void setScopeName(String scopeName) {
        this.scopeName = scopeName;
    }
}
