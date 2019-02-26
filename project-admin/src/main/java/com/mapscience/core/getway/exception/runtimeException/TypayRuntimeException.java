package com.mapscience.core.getway.exception.runtimeException;

import com.mapscience.core.util.Message;

public class TypayRuntimeException extends BaseRuntimeException{
    private static final long serialVersionUID = -5728315783883342709L;

    public TypayRuntimeException(Message response, String string){
        super(response, string);
    }

    /**
     * 默认成功
     * @return
     */
    public static TypayRuntimeException newInstance(){
        return new TypayRuntimeException(new Message(200, "处理成功"),"");
    }
    /**
     * 自定义是错误类
     * keapBaseEnum
     * @return
     */
    public static TypayRuntimeException newInstance(Message response){
        return new TypayRuntimeException(response,"");
    }
    /**
     * 自定义是错误类
     *  keapBaseEnum
     * @param message
     * @return
     */
    public static TypayRuntimeException newInstance(Message response,String message){
        return new TypayRuntimeException(response,message);
    }
}
