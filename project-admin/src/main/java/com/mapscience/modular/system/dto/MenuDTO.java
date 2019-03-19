package com.mapscience.modular.system.dto;

import com.mapscience.modular.system.model.Menu;

public class MenuDTO extends Menu {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
