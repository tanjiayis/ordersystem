package com.example.demo.order.admin.service;

import com.example.demo.order.admin.mapper.UserMapper;
import com.example.demo.order.admin.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * Created by JIAYI_TAN on 2019/2/22.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User validateLogin(String username, String password) {
        Condition condition = new Condition(User.class);
        condition.createCriteria().andCondition("username=", username)
                .andCondition("password=", password);
        List<User> list = userMapper.selectByExample(condition);
        return list.size()==0?null:list.get(0);
    }
}
