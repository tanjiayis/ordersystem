package com.example.demo.order.admin.controller;

import com.example.demo.controller.BaseController;
import com.example.demo.order.admin.dto.LoginDto;
import com.example.demo.exception.IllegalArgumentException;
import com.example.demo.order.admin.pojo.User;
import com.example.demo.order.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by JIAYI_TAN on 2019/3/10.
 */
@Controller
@RequestMapping("/admin")
public class AdminWebController extends BaseController {
    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    public String webLogin(@Valid LoginDto dto, ModelMap model, BindingResult bindingResult, HttpServletRequest request){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        User user= userService.validateLogin(dto.getUsername(), dto.getPassword());
        if (user == null) return "redirect:login";
        request.getSession().setAttribute("user", user);
        model.put("user", user);
        return "/admin/index";
    }
    @RequestMapping("")
    public String login(ModelMap model){
        model.put("title", "点餐系统后台管理登录");
        return "/admin/login";
    }
    @RequestMapping("/top")
    public String top(HttpServletRequest request, ModelMap model){
        User user = (User) request.getSession().getAttribute("user");
        model.put("user", user);
        return "/admin/top";
    }
    @RequestMapping("/left")
    public String left(){
        return "/admin/left";
    }
    @RequestMapping("/welcome")
    public String welcome(){
        return "/welcome";
    }
}
