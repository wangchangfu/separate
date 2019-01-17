package com.mapscience.modular.system.controller;


import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.common.constant.Constant;
import com.mapscience.core.common.status.ProjectStatusEnum;
import com.mapscience.core.util.AesCipherUtil;
import com.mapscience.core.util.JedisUtil;
import com.mapscience.core.util.JwtUtil;
import com.mapscience.modular.system.model.Employee;
import com.mapscience.modular.system.service.IEmployeeService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 员工信息表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@RestController
@RequestMapping("/employee")
@PropertySource("classpath:jwt.properties")
public class EmployeeController {
    /**
     * RefreshToken过期时间
     */
    @Value("${refreshTokenExpireTime}")
    private String refreshTokenExpireTime;

    @Autowired
    private IEmployeeService employeeService;
    /**
     * 用户登录
     * @param employee
     * @return
     */
    @PostMapping("login")
    public ResponseVal login(@RequestBody Employee employee){
        //验证是否为空
        if(ObjectUtils.isEmpty(employee.getAccount())){
            return  new ResponseVal(ProjectStatusEnum.NO_THIS_USER.getCode(),ProjectStatusEnum.NO_THIS_USER.getMsg());
        }
        if (ObjectUtils.isEmpty(employee.getPassWord())){
            return  new ResponseVal(ProjectStatusEnum.USE_PASSWORD_NO.getCode(),ProjectStatusEnum.USE_PASSWORD_NO.getMsg());
        }
        //清除空格
        employee.setAccount(employee.getAccount().trim());
        employee.setPassWord(employee.getPassWord().trim());
        String key = AesCipherUtil.enCrypto(employee.getPassWord());
        Employee emp = this.employeeService.getEmployeeByAccountAndPasswd(employee.getAccount(), key);
        if (ObjectUtils.isEmpty(emp)){
            return new ResponseVal(ProjectStatusEnum.USER_NOT_EXISTED.getCode(), ProjectStatusEnum.USER_NOT_EXISTED.getMsg());
        }
        // 清除可能存在的Shiro权限信息缓存
        if (JedisUtil.exists(Constant.PREFIX_SHIRO_CACHE + emp.getAccount())) {
            JedisUtil.delKey(Constant.PREFIX_SHIRO_CACHE + emp.getAccount());
        }
        // 设置RefreshToken，时间戳为当前时间戳，直接设置即可(不用先删后设，会覆盖已有的RefreshToken)
        String currentTimeMillis = String.valueOf(System.currentTimeMillis());
        JedisUtil.setObject(Constant.PREFIX_SHIRO_REFRESH_TOKEN + emp.getAccount(), currentTimeMillis, Integer.parseInt(refreshTokenExpireTime));
        // 从Header中Authorization返回AccessToken，时间戳为当前时间戳
        String token = JwtUtil.sign(emp.getAccount(), currentTimeMillis);
        return new ResponseVal(HttpStatus.OK.value(), "登录成功");
    }

}

