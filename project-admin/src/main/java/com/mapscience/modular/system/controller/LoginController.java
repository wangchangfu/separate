package com.mapscience.modular.system.controller;

import com.mapscience.core.base.controller.BaseController;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.common.constant.Constant;
import com.mapscience.core.common.status.ProjectStatusEnum;
import com.mapscience.core.exception.ProjectException;
import com.mapscience.core.util.AesCipherUtil;
import com.mapscience.core.util.JedisUtil;
import com.mapscience.core.util.JwtUtil;
import com.mapscience.modular.system.model.Employee;
import com.mapscience.modular.system.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@PropertySource("classpath:jwt.properties")
public class LoginController extends BaseController {

    @Autowired
    private IEmployeeService employeeService;

    @Value("refreshTokenExpireTime")
    private String refreshTokenExcepireTime;

    @PostMapping(value = "login")
    public ResponseVal login(Employee user){
        if (user!=null){
            if(user.getAccount()!=null && "".equals(user.getAccount())) {
                user.setAccount(user.getAccount().trim());
            }
            if (user.getPassWord() !=null && "".equals(user.getPassWord())){
                user.setPassWord(user.getPassWord().trim());
            }
        }
        //查询用户
        Employee users=employeeService.getByAccount(user.getAccount());
        //对比密码
        String key = AesCipherUtil.deCrypto(users.getPassWord());
        if (key.equals(user.getAccount() + user.getPassWord())) {
            // 清除可能存在的Shiro权限信息缓存
            if (JedisUtil.exists(Constant.PREFIX_SHIRO_CACHE + user.getAccount())) {
                JedisUtil.delKey(Constant.PREFIX_SHIRO_CACHE + user.getAccount());
            }
            // 设置RefreshToken，时间戳为当前时间戳，直接设置即可(不用先删后设，会覆盖已有的RefreshToken)
            String currentTimeMillis = String.valueOf(System.currentTimeMillis());
            JedisUtil.setObject(Constant.PREFIX_SHIRO_REFRESH_TOKEN + user.getAccount(), currentTimeMillis, Integer.parseInt(refreshTokenExcepireTime));
            // 从Header中Authorization返回AccessToken，时间戳为当前时间戳
            String token = JwtUtil.sign(user.getAccount(), currentTimeMillis);
            users.setToken(token);
            return new ResponseVal(HttpStatus.OK.value(), "登录成功", users);
        } else {
            throw new ProjectException(ProjectStatusEnum.USER_NOT_EXISTED);
        }

    }

}
