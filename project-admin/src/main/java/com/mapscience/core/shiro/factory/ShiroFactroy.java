package com.mapscience.core.shiro.factory;

import com.mapscience.core.common.constant.factory.ConstantFactory;
import com.mapscience.core.common.constant.state.ManagerStatus;
import com.mapscience.core.shiro.ShiroUser;
import com.mapscience.core.util.Convert;
import com.mapscience.core.util.SpringContextHolder;
import com.mapscience.modular.system.mapper.EmployeeMapper;
import com.mapscience.modular.system.model.Employee;
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

    @Autowired
    private EmployeeMapper employeeMapper;

//    @Autowired
//    private MenuMapper menuMapper;

    public static IShiro me() {
        return SpringContextHolder.getBean(IShiro.class);
    }

    @Override
    public Employee employee(String account) {
        Employee user = employeeMapper.getByAccount(account);

        // 账号不存在
        if (null == user) {
            throw new CredentialsException();
        }
        // 账号被冻结
        if (user.getType() != ManagerStatus.OK.getCode()) {
            throw new LockedAccountException();
        }
        return user;
    }

    @Override
    public ShiroUser shiroUser(Employee employee) {
        ShiroUser shiroUser = new ShiroUser();
        shiroUser.setId(employee.getEmployeeId());
        shiroUser.setAccount(employee.getAccount());
//        shiroUser.setDeptId(employee.getDeptid());
//        shiroUser.setDeptName(ConstantFactory.me().getDeptName(user.getDeptid()));
        shiroUser.setName(employee.getEmployeeName());
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

    @Override
    public List<String> findPermissionsByRoleId(String roleId) {

        return null;
//                menuMapper.getResUrlsByRoleId(roleId);
    }

    @Override
    public String findRoleNameByRoleId(String roleId) {
        return ConstantFactory.me().getSingleRoleTip(roleId);
    }

    @Override
    public SimpleAuthenticationInfo info(ShiroUser shiroUser, Employee employee, String realmName) {
        String credentials = employee.getPassWord();
        // 密码加盐处理
        String source = employee.getPassWord();
        ByteSource credentialsSalt = new Md5Hash(source);
        return new SimpleAuthenticationInfo(shiroUser, credentials, credentialsSalt, realmName);
    }
}
