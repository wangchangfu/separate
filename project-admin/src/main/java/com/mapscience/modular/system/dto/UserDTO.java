package com.mapscience.modular.system.dto;

import com.mapscience.modular.system.model.User;

public class UserDTO extends User {

    private static final long serialVersionUID = 8968736376217998447L;

    private String comName;
    private String roleId;  //角色ID

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }
}
