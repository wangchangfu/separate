package com.mapscience.modular.system.controller;


import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.common.constant.state.ManagerStatus;
import com.mapscience.core.shiro.ShiroKit;
import com.mapscience.modular.system.model.User;
import com.mapscience.modular.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-03-05
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 添加管理员
     * @param user
     * @return
     */
    @RequestMapping(value = "/addUser" ,method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseVal addUser(@RequestBody User user){
        //验证用户是否被注册
//        User byAccount = userService.getByAccount(user);
//        if (byAccount != null) {
//            throw new ProjectException(ProjectStatusEnum.USER_ALREADY_REG);
//        }
        // 完善账号信息
        user.setSalt(ShiroKit.getRandomSalt(5));
        //user.setPassword(ShiroKit.md5(user.getPassword(), user.getSalt()));
        user.setPassword(user.getPassword());
        user.setPassword(user.getPassword());
        user.setStatus(ManagerStatus.OK.getCode());
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        //添加用户
        ResponseVal responseVal = userService.addUser(user);
        return responseVal;
    }

}

