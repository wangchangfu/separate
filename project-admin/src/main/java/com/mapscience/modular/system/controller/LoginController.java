package com.mapscience.modular.system.controller;

import com.mapscience.core.base.controller.BaseController;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.common.constant.Constant;
import com.mapscience.core.common.status.ProjectStatusEnum;
import com.mapscience.core.shiro.ShiroKit;
import com.mapscience.core.shiro.ShiroUser;
import com.mapscience.core.util.JedisUtil;
import com.mapscience.core.util.JwtUtil;
import com.mapscience.modular.system.model.Employee;
import com.mapscience.modular.system.model.User;
import com.mapscience.modular.system.service.IEmployeeService;
import com.mapscience.modular.system.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
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

        if(users==null){
            return new ResponseVal(HttpStatus.FOUND.value(),"账号不存在");
        }
        //验证
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword().toCharArray());
        SimpleAuthenticationInfo sim = new SimpleAuthenticationInfo( new ShiroUser(), users.getPassword(), new Md5Hash(users.getSalt()), "");
        //校验账号
        HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher();
        md5CredentialsMatcher.setHashAlgorithmName(ShiroKit.hashAlgorithmName);//MD5
        md5CredentialsMatcher.setHashIterations(ShiroKit.hashIterations);//1024
        boolean passwordTrueFlag = md5CredentialsMatcher.doCredentialsMatch(token, sim);//验证

            if(passwordTrueFlag) {
                // 清除可能存在的Shiro权限信息缓存
                if (JedisUtil.exists(Constant.PREFIX_SHIRO_CACHE + user.getUsername())) {
                    JedisUtil.delKey(Constant.PREFIX_SHIRO_CACHE + user.getUsername());
                }
                // 设置RefreshToken，时间戳为当前时间戳，直接设置即可(不用先删后设，会覆盖已有的RefreshToken)
                String currentTimeMillis = String.valueOf(System.currentTimeMillis());
                JedisUtil.setObject(Constant.PREFIX_SHIRO_REFRESH_TOKEN + user.getUsername(), currentTimeMillis, Integer.parseInt(refreshTokenExcepireTime));
                // 从Header中Authorization返回AccessToken，时间戳为当前时间戳
                String token1 = JwtUtil.sign(user.getUsername(), currentTimeMillis);
                users.setToken(token1);
                return new ResponseVal(0, "登陆成功", users);
            }else{
                return new ResponseVal(500,"密码错误");
            }
    }


    /**
     * 退出登录
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseVal logOut() {
        /*LogManager.me().executeLog(LogTaskFactory.exitLog(ShiroKit.getUser().getId(), getIp()));
        ShiroKit.getSubject().logout();
        deleteAllCookie();*/
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Employee employee = (Employee)session.getAttribute("emp");
        if (JedisUtil.exists(Constant.PREFIX_SHIRO_REFRESH_TOKEN + employee.getAccount())) {
            if (JedisUtil.delKey(Constant.PREFIX_SHIRO_REFRESH_TOKEN + employee.getAccount()) > 0) {
                return super.responseBody(ProjectStatusEnum.SUCCESS);
            }
        }
        return super.responseBody(ProjectStatusEnum.KICK_OUT_ERROR);
    }

}
