package com.mapscience.core.getway;

import java.io.Serializable;

public class BaseWayRequest implements Serializable {
    private static final long serialVersionUID = 4605478602625219731L;

    public BaseWayRequest(){}

    private String method; //外调方法
    private String version = CommConst.VERSION_1_0_2;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
