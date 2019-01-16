package com.mapscience.core.exception;

import com.mapscience.core.common.status.ProjectStatusEnum;

/**
 * 异常封装
 */
public class ProjectException extends RuntimeException {
    private Integer code;

    private String msg;

    public ProjectException(ProjectStatusEnum projectStatusEnum) {
        this.code = projectStatusEnum.getCode();
        this.msg = projectStatusEnum.getMsg();
    }

    public ProjectException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
