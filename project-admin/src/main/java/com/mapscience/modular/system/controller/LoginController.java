package com.mapscience.modular.system.controller;

import com.mapscience.core.base.controller.BaseController;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.common.constant.Constant;
import com.mapscience.core.common.status.ProjectStatusEnum;
import com.mapscience.core.exception.ProjectException;
import com.mapscience.core.log.LogManager;
import com.mapscience.core.log.factory.LogTaskFactory;
import com.mapscience.core.shiro.ShiroKit;
import com.mapscience.core.util.AesCipherUtil;
import com.mapscience.core.util.JedisUtil;
import com.mapscience.core.util.JwtUtil;
import com.mapscience.modular.system.model.User;
import com.mapscience.modular.system.service.IEmployeeService;
import com.mapscience.modular.system.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.mapscience.core.support.HttpKit.getIp;

@Controller
@PropertySource("classpath:jwt.properties")
public class LoginController extends BaseController {

    @Autowired
    private IEmployeeService employeeService;

    /**
     * 管理员
     */
    @Autowired
    private IUserService userService;


    @Value("refreshTokenExpireTime")
    private String refreshTokenExcepireTime;

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
    @RequestMapping(value = "/ajaxlogin", method = RequestMethod.POST)
    public ResponseVal login(User user){
     if (user!=null){
            if(user.getAccount()!=null && "".equals(user.getAccount())) {
                user.setAccount(user.getAccount().trim());
            }
            if (user.getPassword() !=null && "".equals(user.getPassword())){
                user.setPassword(user.getPassword().trim());
            }
        }
        //查询用户
        User users = userService.getByAccount(user.getAccount());

        if(users==null){
            return new ResponseVal(HttpStatus.FOUND.value(),"账号不存在");
        }
        //对比密码
        String key = AesCipherUtil.deCrypto(users.getPassword());
        if(key.equals(user.getAccount() + user.getPassword())){
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
            return new ResponseVal(HttpStatus.OK.value(),"登陆成功",users);
        }else {
            throw new ProjectException(ProjectStatusEnum.USER_NOT_EXISTED);
        }
    }


    /**
     * 退出登录
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut() {
        LogManager.me().executeLog(LogTaskFactory.exitLog(ShiroKit.getUser().getId(), getIp()));
        ShiroKit.getSubject().logout();
        deleteAllCookie();
        return REDIRECT + "/login";
    }

}
