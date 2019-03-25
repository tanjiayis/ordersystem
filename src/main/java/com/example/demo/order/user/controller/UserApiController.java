package com.example.demo.order.user.controller;

import com.example.demo.controller.BaseApiController;
import com.example.demo.data.CommonResult;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.IllegalArgumentException;
import com.example.demo.order.user.dto.DelUserDto;
import com.example.demo.order.user.dto.UpdateUserDto;
import com.example.demo.order.user.pojo.User;
import com.example.demo.order.user.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by JIAYI_TAN on 2019/3/23.
 */
@RestController
@RequestMapping("/api/user")
public class UserApiController extends BaseApiController {
    @Autowired
    private UserService userService;
    @RequestMapping("/delete")
    @RequiresAuthentication
    public CommonResult delete(@Valid DelUserDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        userService.deleteUser(dto.getId());
        return new CommonResult();
    }
    @RequiresAuthentication
    @RequestMapping("/find")
    public CommonResult find(@Valid DelUserDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        User user = userService.findUser(dto.getId());
        return new CommonResult(user);
    }
    @RequestMapping("/add")
    public CommonResult add(@Valid UpdateUserDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        if (dto.getRoleId() == 0) throw new BusinessException("请选择角色");
        userService.add(dto.getUsername(), dto.getPassword(), dto.getRealName(), dto.getGender(), dto.getRoleId(), dto.getMobile());
        return new CommonResult();
    }
    @RequestMapping("/update")
    public CommonResult update(@Valid UpdateUserDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        userService.update(dto.getUserId(), dto.getUsername(), dto.getRealName(), dto.getGender(), dto.getRoleId(), dto.getMobile());
        return  new CommonResult();
    }
}
