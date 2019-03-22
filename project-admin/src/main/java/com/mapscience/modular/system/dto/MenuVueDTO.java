package com.mapscience.modular.system.dto;

import java.util.List;
import java.util.Map;

public class MenuVueDTO  {
    private static final long serialVersionUID = 38457198757622835L;




    private String path;  //
    private String redirect;  //重定向
    private String component1;  //固定父组件
    private Map   meta;
    private String title;   //名称
    private String icon;
    private List<ChildrenMenu> children;
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }






    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getComponent1() {
        return component1;
    }

    public void setComponent1(String component1) {
        this.component1 = component1;
    }

    public Map getMeta() {
        return meta;
    }

    public void setMeta(Map meth) {
        this.meta = meth;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ChildrenMenu> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenMenu> children) {
        this.children = children;
    }
}
