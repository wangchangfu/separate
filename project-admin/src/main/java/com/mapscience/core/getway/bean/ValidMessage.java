package com.mapscience.core.getway.bean;

public class ValidMessage {

    protected boolean valid;

    protected String message;

    protected Object data;

    public ValidMessage(){}

    public ValidMessage(boolean valid, String message, Object data){
        super();
        this.data=data;
        this.message=message;
        this.valid=valid;
    }

    public ValidMessage(boolean valid, String message){
        super();
        this.message=message;
        this.valid=valid;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
