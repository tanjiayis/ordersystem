package com.example.demo.order.user.mapper;

import com.example.demo.order.user.model.UserInfo;
import com.example.demo.order.user.pojo.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import javax.persistence.Entity;
import java.util.List;

/**
 * Created by JIAYI_TAN on 2019/2/22.
 */
public interface UserMapper extends Mapper<User> {
    List<UserInfo> findUsers(@Param("roleId") int roleId, @Param("realName") String realName);
}
