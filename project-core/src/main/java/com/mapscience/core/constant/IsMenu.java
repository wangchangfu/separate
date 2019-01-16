package com.mapscience.core.constant;

public enum IsMenu {
    YES(1, "是"),
    NO(0, "不是"); //不是菜单的是按钮
    int code;
    String msg;

    IsMenu(int code, String message) {
        this.code = code;
        this.msg = msg;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static String valueOf(Integer status) {
        if (status == null) {
            return "";
        } else {
            for (IsMenu s : IsMenu.values()) {
                if (s.getCode() == status) {
                    return s.getMsg();
                }
            }
            return "";
        }
    }
}
