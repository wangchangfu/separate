package com.mapscience.modular.system.controller;


import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.common.status.ProjectStatusEnum;
import com.mapscience.core.exception.ProjectException;
import com.mapscience.core.util.ObjectUtil;
import com.mapscience.modular.system.dto.UserDTO;
import com.mapscience.modular.system.model.User;
import com.mapscience.modular.system.service.IUserRoleService;
import com.mapscience.modular.system.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-03-05
 */
@Api(tags="管理员表控制器")
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;


    @Autowired
    private IUserRoleService userRoleService;
    /**
     * 添加管理员 传参角色ID，公司ID，管理员User
     * @param user
     * @return
     */
    @ApiOperation(value = "添加管理员")
    @RequestMapping(value = "/addUser" ,method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseVal addUser(@RequestBody UserDTO user){
        //验证用户是否被注册
            User byAccount = userService.getByAccount(user.getUsername());
            if (byAccount != null) {
                 throw new ProjectException(ProjectStatusEnum.USER_ALREADY_REG);
            }
        //添加用户
        ResponseVal responseVal = userService.addUser(user);
        return responseVal;
    }

    /**
     * 按照公司查找管理员
     * @param user
     * @return
     */
    @ApiOperation(value = "按照公司查找管理员")
    @RequestMapping(value = "/findByComUser" ,method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseVal findByComUser(@RequestBody User user){
        List<User> byComUser = this.userService.findByComUser(user);
        if(ObjectUtil.isEmpty(byComUser)){
            return new ResponseVal(HttpStatus.FOUND.value(),"暂无数据");
        }
        return new ResponseVal("查找成功",byComUser);
    }

}

