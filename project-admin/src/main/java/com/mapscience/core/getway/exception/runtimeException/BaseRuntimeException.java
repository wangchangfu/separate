package com.mapscience.core.getway.exception.runtimeException;

import com.mapscience.core.util.Message;
import org.apache.commons.lang.StringUtils;

public class BaseRuntimeException extends RuntimeException{

    private static final long serialVersionUID = 5086178465451018186L;

    private Message message;

    public BaseRuntimeException(){}

    public BaseRuntimeException(String arg){
        super(arg);
    }

    public BaseRuntimeException(Throwable arg){
        super(arg);
    }

    public BaseRuntimeException(String arg0, Throwable arg1){
        super(arg0, arg1);
    }

    public BaseRuntimeException(Message response, String message){
        super(message);
        this.message=response;
    }

    public Message getMsg(){
        return this.message;
    }

    public String getMessage(){
        return StringUtils.defaultIfEmpty(super.getMessage(), this.message.MESSAGE);
    }
}
