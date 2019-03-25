package com.example.demo.shiro;

import com.example.demo.order.privilege.mapper.RoleMapper;
import com.example.demo.order.user.mapper.UserMapper;
import com.example.demo.order.privilege.pojo.Role;
import com.example.demo.order.user.pojo.User;
import com.example.demo.order.privilege.service.PrivilegeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.Set;

/**
 * Created by JIAYI_TAN on 2019/3/7.
 */
@Component
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PrivilegeService privilegeService;
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = String.valueOf(token.getPassword());
        Condition condition = new Condition(User.class);
        Example.Criteria criteria = condition.createCriteria();
        if (username != null || username!= "") criteria.andCondition("username=", username);
        User user = userMapper.selectByExample(condition).get(0);
        if (user == null) throw new AuthenticationException("用户不存在");
        if (!user.getPassword().equals(password) || user.getPassword() == null) throw new AuthenticationException("无效的用户密码");
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("user", user);
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user  = (User) principalCollection.getPrimaryPrincipal();
        if (user == null) return null;
        SecurityUtils.getSubject().getSession().setAttribute(String.valueOf(user.getId()), SecurityUtils.getSubject().getPrincipals());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Role role = roleMapper.selectByPrimaryKey(user.getId());
        authorizationInfo.addRole(role.getName());
        Set<String> permissions = privilegeService.getRolePermissions(role.getId());
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }
}
