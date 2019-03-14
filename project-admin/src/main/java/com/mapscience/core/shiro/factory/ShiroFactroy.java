package com.mapscience.core.shiro.factory;

import com.mapscience.core.common.constant.factory.ConstantFactory;
import com.mapscience.core.common.constant.state.ManagerStatus;
import com.mapscience.core.shiro.ShiroUser;
import com.mapscience.core.util.SpringContextHolder;
import com.mapscience.modular.system.mapper.UserMapper;
import com.mapscience.modular.system.model.User;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@DependsOn("springContextHolder")
@Transactional(readOnly = true)
public class ShiroFactroy implements IShiro {

    //@Autowired
    //private EmployeeMapper employeeMapper;

    @Autowired
    private UserMapper userMapper;

//    @Autowired
//    private MenuMapper menuMapper;

    public static IShiro me() {
        return SpringContextHolder.getBean(IShiro.class);
    }

    /**
     * 查找账号是否存在
     * @param account 账号
     * @return
     */
    @Override
    public User user(String account) {
        User user = userMapper.getByAccount(account);

        // 账号不存在
        if (null == user) {
            throw new CredentialsException();
        }
        // 账号被冻结
        if (user.getStatus() != ManagerStatus.OK.getCode()) {
            throw new LockedAccountException();
        }
        return user;
    }



    @Override
    public ShiroUser shiroUser(User user) {
        ShiroUser shiroUser = new ShiroUser();
        shiroUser.setId(user.getUserId());
        shiroUser.setAccount(user.getUsername());
//        shiroUser.setDeptId(employee.getDeptid());
//        shiroUser.setDeptName(ConstantFactory.me().getDeptName(user.getDeptid()));
        shiroUser.setName(user.getEmpName());
//        String[] roleArray = Convert.toStrArray(user.getRoleid());
        List<String> roleList = new ArrayList<String>();
        List<String> roleNameList = new ArrayList<String>();
//        for (String roleId : roleArray) {
//            roleList.add(roleId);
//            roleNameList.add(ConstantFactory.me().getSingleRoleName(roleId));
//        }
        shiroUser.setRoleList(roleList);
        shiroUser.setRoleNames(roleNameList);
        return shiroUser;
    }

    /**
     * 获取权限列表通过角色id
     * @param roleId 角色id
     * @return
     */
    @Override
    public List<String> findPermissionsByRoleId(String roleId) {

        return null;
//                menuMapper.getResUrlsByRoleId(roleId);
    }

    /**
     * 根据角色id获取角色名称
     * @param roleId 角色id
     * @return
     */
    @Override
    public String findRoleNameByRoleId(String roleId) {
        return ConstantFactory.me().getSingleRoleTip(roleId);
    }

    /**
     * 获取shiro的认证信息
     * @param shiroUser
     * @param employee
     * @param realmName
     * @return
     */
    @Override
    public SimpleAuthenticationInfo info(ShiroUser shiroUser, User employee, String realmName) {
        String credentials = employee.getPassword();
        // 密码加盐处理
        String source = employee.getPassword();
        ByteSource credentialsSalt = new Md5Hash(source);
        return new SimpleAuthenticationInfo(shiroUser, credentials, credentialsSalt, realmName);
    }
}
