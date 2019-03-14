package com.mapscience.modular.system.controller;

import com.mapscience.core.base.controller.BaseController;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.common.constant.Constant;
import com.mapscience.core.common.status.ProjectStatusEnum;
import com.mapscience.core.util.AesCipherUtil;
import com.mapscience.core.util.JedisUtil;
import com.mapscience.core.util.JwtUtil;
import com.mapscience.modular.system.model.User;
import com.mapscience.modular.system.service.IEmployeeService;
import com.mapscience.modular.system.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@PropertySource("classpath:jwt.properties")
public class LoginController extends BaseController {

    /**
     * 普通用户
     */
    @Autowired
    private IEmployeeService employeeService;

    /**
     * 管理员
     */
    @Autowired
    private IUserService userService;


   // @Value("refreshTokenExpireTime")
    private String refreshTokenExcepireTime="1800";

    //默认路径
    private final String PREFIX = "/modular/";


    /**
     * 登录页面
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String tologin() {
       return "/login";
    }

    /**
     * 登陆成功跳转到大屏
     * @return
     */
    @RequestMapping("/home")
    public String index(){
        return PREFIX+"/home";
    }

    /**
     * 登陆验证
     * @param user
     * @return
     */
    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVal login(@RequestBody User user){
        if (user!=null){
            if(user.getUsername()!=null && "".equals(user.getUsername())) {
                user.setUsername(user.getUsername().trim());
            }
            if (user.getPassword() !=null && "".equals(user.getPassword())){
                user.setPassword(user.getPassword().trim());
            }
        }
        //查询用户
        User users = userService.getByAccount(user.getUsername());
        String key = AesCipherUtil.deCrypto(users.getPassword());
        if(key.equals(user.getUsername()+user.getPassword())) {
                // 清除可能存在的Shiro权限信息缓存
                if (JedisUtil.exists(Constant.PREFIX_SHIRO_CACHE + user.getUsername())) {
                    JedisUtil.delKey(Constant.PREFIX_SHIRO_CACHE + user.getUsername());
                }
                // 设置RefreshToken，时间戳为当前时间戳，直接设置即可(不用先删后设，会覆盖已有的RefreshToken)
                String currentTimeMillis = String.valueOf(System.currentTimeMillis());
                JedisUtil.setObject(Constant.PREFIX_SHIRO_REFRESH_TOKEN + user.getUsername(), currentTimeMillis, Integer.parseInt(refreshTokenExcepireTime));
                // 从Header中Authorization返回AccessToken，时间戳为当前时间戳
                String token = JwtUtil.sign(user.getUsername(), currentTimeMillis);
                users.setToken(token);
                return new ResponseVal(0, "登陆成功", users);
            }else{
                return new ResponseVal(500,"密码错误");
            }
    }


    /**
     * 退出登录
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVal logOut(@RequestBody User user) {

        //查询用户
        User users = userService.getById(user.getUserId());
        if (JedisUtil.exists(Constant.PREFIX_SHIRO_REFRESH_TOKEN + users.getUsername())) {
            if (JedisUtil.delKey(Constant.PREFIX_SHIRO_REFRESH_TOKEN + users.getUsername()) > 0) {
                return super.responseBody(ProjectStatusEnum.SUCCESS);
            }
        }
        return super.responseBody(ProjectStatusEnum.KICK_OUT_ERROR);
    }
}
