package com.example.demo.shiro;

import com.example.demo.order.admin.mapper.UserMapper;
import com.example.demo.order.admin.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

/**
 * Created by JIAYI_TAN on 2019/3/7.
 */
@Component
public class UserRealm extends AuthenticatingRealm {
    @Autowired
    private UserMapper userMapper;
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
        return new SimpleAuthenticationInfo();
    }
}
