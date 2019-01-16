package com.mapscience.core.aop;

import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.exception.ProjectException;
import com.mapscience.core.shiro.ShiroKit;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 补货全局异常
 */
@ControllerAdvice
@RestControllerAdvice
@Order(-1)
public class GlobalExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());


    /**
     * 拦截业务异常
     */
    @ExceptionHandler(ProjectException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseVal notFount(ProjectException e) {
        ShiroKit.getUser();
        //LogManager.me().executeLog(LogTaskFactory.exceptionLog(ShiroKit.getUser().getId(), e));
        //HttpKit.getRequest().setAttribute("tip", e.getMessage());
        log.error("无权访问(Unauthorized):", e);
        return new ResponseVal(e.getCode(), e.getMsg());
    }

    /**
     * 未授权调用方法
     * @param authorizationException
     * @return
     */
    /*@ExceptionHandler(ShiroException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseVal authException(AuthorizationException authorizationException){
        LogManager.me().executeLog(LogTaskFactory.exceptionLog(ShiroKit.getUser().getId(), authorizationException));
        log.error("未授权调用方法",authorizationException);
        return new ResponseVal(ProjectStatusEnum.NOT_AUTHORIZED_TO_INVOKE_METHOD.getCode(),ProjectStatusEnum.NOT_AUTHORIZED_TO_INVOKE_METHOD.getMsg());
    }*/


    /**
     * 捕捉所有Shiro异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public ResponseVal handle401(ShiroException e) {
        log.error("无权访问(Unauthorized):" + e.getMessage(), e);
        return new ResponseVal(HttpStatus.UNAUTHORIZED.value(), "无权访问(Unauthorized):" + e.getMessage());
    }


    /**
     * 单独捕捉Shiro(UnauthorizedException)异常
     * 该异常为访问有权限管控的请求而该用户没有所需权限所抛出的异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseVal handle401(UnauthorizedException e) {
        log.error("无权访问(Unauthorized):当前Subject没有此请求所需权限(" + e.getMessage() + ")", e);
        return new ResponseVal(HttpStatus.UNAUTHORIZED.value(), "无权访问(Unauthorized):当前Subject没有此请求所需权限(" + e.getMessage() + ")");
    }

    /**
     * 单独捕捉Shiro(UnauthenticatedException)异常
     * 该异常为以游客身份访问有权限管控的请求无法对匿名主体进行授权，而授权失败所抛出的异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthenticatedException.class)
    public ResponseVal handle401(UnauthenticatedException e) {
        log.error("无权访问(Unauthorized):当前Subject是匿名Subject，请先登录(This subject is anonymous.)", e);
        return new ResponseVal(HttpStatus.UNAUTHORIZED.value(), "无权访问(Unauthorized):当前Subject是匿名Subject，请先登录(This subject is anonymous.)");
    }

    /**
     * 捕捉校验异常(BindException)
     *
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ResponseVal validException(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        Map<String, Object> result = this.getValidError(fieldErrors);
        log.error("无权访问(Unauthorized):当前Subject是匿名Subject，请先登录(This subject is anonymous.)", e);
        return new ResponseVal(HttpStatus.BAD_REQUEST.value(), result.get("errorMsg").toString(), result.get("errorList"));
    }

    /**
     * 捕捉校验异常(MethodArgumentNotValidException)
     *
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseVal validException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        Map<String, Object> result = this.getValidError(fieldErrors);
        return new ResponseVal(HttpStatus.BAD_REQUEST.value(), result.get("errorMsg").toString(), result.get("errorList"));
    }


    /**
     * 捕捉404异常
     *
     * @return
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseVal handle(NoHandlerFoundException e) {
        return new ResponseVal(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }


    /**
     * 捕捉其他所有异常
     * @param request
     * @param ex
     * @return
     */
    /*@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseVal globalException(HttpServletRequest request, Throwable ex) {
        return new ResponseVal(this.getStatus(request).value(), ex.toString() + ": " + ex.getMessage());
    }*/

    /**
     * 获取状态码
     * @param request
     * @return
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }


    /**
     * 获取校验错误信息
     * @param fieldErrors
     * @return
     */
    private Map<String, Object> getValidError(List<FieldError> fieldErrors) {
        Map<String, Object> result = new HashMap<String, Object>(16);
        List<String> errorList = new ArrayList<String>();
        StringBuffer errorMsg = new StringBuffer("校验异常(ValidException):");
        for (FieldError error : fieldErrors) {
            errorList.add(error.getField() + "-" + error.getDefaultMessage());
            errorMsg.append(error.getField() + "-" + error.getDefaultMessage() + ".");
        }
        result.put("errorList", errorList);
        result.put("errorMsg", errorMsg);
        return result;
    }


}
