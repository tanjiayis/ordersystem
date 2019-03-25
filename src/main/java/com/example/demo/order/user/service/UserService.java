package com.example.demo.order.user.service;

import com.example.demo.exception.BusinessException;
import com.example.demo.order.user.enums.GenderEnum;
import com.example.demo.order.user.mapper.UserMapper;
import com.example.demo.order.user.model.UserInfo;
import com.example.demo.order.user.pojo.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.rmi.MarshalledObject;
import java.util.Date;
import java.util.List;

/**
 * Created by JIAYI_TAN on 2019/2/22.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public PageInfo<UserInfo> findUsers(int roleId, String realname, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<UserInfo> list = userMapper.findUsers(roleId, realname);
        return new PageInfo<>(list);
    }

    public void deleteUser(int id) {
        userMapper.deleteByPrimaryKey(id);
    }

    public User findUser(int userId) {
        User user = User.getUserInfo();
        if (user.getRoleId() != 3 && user.getId() != userId) throw new BusinessException("你不能修改他人信息!");
        return userMapper.selectByPrimaryKey(userId);
    }

    public void add(String username, String password, String realName, GenderEnum gender, int roleId, String mobile) {
        Condition condition = new Condition(User.class);
        condition.createCriteria().andCondition("username=", username);
        List<User> users = userMapper.selectByExample(condition);
        if (users.size() > 0) throw new BusinessException("此用户名已经存在!");
        password = StringUtil.isEmpty(password)?"123456":password;
        Date date = new Date();
        User user = new User(roleId, username, password, gender, realName, mobile, date, date);
        userMapper.insert(user);
    }

    public void update(int userId, String username, String realName, GenderEnum gender, int roleId, String mobile) {
        User user = userMapper.selectByPrimaryKey(userId);
        roleId = roleId == 0 ? user.getRoleId() : roleId;
        user.setUsername(username);
        user.setGender(gender);
        user.setRoleId(roleId);
        user.setMobile(mobile);
        user.setRealName(realName);
        user.setUpdateTime(new Date());
        userMapper.updateByPrimaryKey(user);
    }
}
