package com.mapscience.core.support.exception;

import com.mapscience.core.support.StrKit;

/**
 * 工具类初始化异常
 */
public class ToolBoxException extends RuntimeException {
    public ToolBoxException(Throwable e) {
        super(e.getMessage(), e);
    }

    public ToolBoxException(String message) {
        super(message);
    }

    public ToolBoxException(String messageTemplate, Object... params) {
        super(StrKit.format(messageTemplate, params));
    }

    public ToolBoxException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ToolBoxException(Throwable throwable, String messageTemplate, Object... params) {
        super(StrKit.format(messageTemplate, params), throwable);
    }
}
