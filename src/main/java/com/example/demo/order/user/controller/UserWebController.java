package com.example.demo.order.user.controller;

import com.example.demo.controller.BaseController;
import com.example.demo.data.CommonPage;
import com.example.demo.exception.IllegalArgumentException;
import com.example.demo.order.privilege.service.RoleService;
import com.example.demo.order.user.dto.FilterUserDto;
import com.example.demo.order.user.model.UserInfo;
import com.example.demo.order.user.pojo.User;
import com.example.demo.order.user.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by JIAYI_TAN on 2019/3/23.
 */
@Controller
@RequestMapping("/privilege")
public class UserWebController extends BaseController{
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @RequestMapping("/users")
    public String userList(@Valid FilterUserDto dto, ModelMap model, BindingResult bindingResult, CommonPage page){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        PageInfo<UserInfo> list = userService.findUsers(dto.getRoleId(), dto.getRealName(), page.getPageIndex(), page.getPageSize());
        model.put("users", list.getList());
        model.put("roles", roleService.findAllRole());
        model.put("param", dto.wrapAsMap());
        model.put("page", new CommonPage(list));
        return "/admin/privilege/users";
    }
}
