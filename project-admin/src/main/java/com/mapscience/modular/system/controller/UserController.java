package com.mapscience.modular.system.controller;


import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.common.constant.state.ManagerStatus;
import com.mapscience.core.common.status.ProjectStatusEnum;
import com.mapscience.core.exception.ProjectException;
import com.mapscience.core.util.AesCipherUtil;
import com.mapscience.modular.system.dto.UserDTO;
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
            User byAccount = userService.getByAccount(user.getUsername());
            if (byAccount != null) {
                 throw new ProjectException(ProjectStatusEnum.USER_ALREADY_REG);
            }
        // 完善账号信息
        //user.setComId(user.getComId());
        //user.setSalt(ShiroKit.getRandomSalt(5));
        user.setPassword(AesCipherUtil.enCrypto(user.getUsername()+user.getPassword()));

        user.setStatus(ManagerStatus.OK.getCode());
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        //添加用户
        ResponseVal responseVal = userService.addUser(user);
        return responseVal;
    }

    /**
     * 按照公司查找管理员
     * @param user
     * @return
     */
    @RequestMapping(value = "/findByComUser" ,method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseVal findByComUser(UserDTO user){
        this.userService.findByComUser(user);
        return null;
    }

}

