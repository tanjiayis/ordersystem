package com.example.demo.order.admin.controller;

import com.example.demo.controller.BaseController;
import com.example.demo.order.admin.dto.LoginDto;
import com.example.demo.exception.IllegalArgumentException;
import com.example.demo.order.privilege.service.RoleService;
import com.example.demo.order.user.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
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
    private RoleService roleService;
    @RequestMapping("/index")
    public String success(){
        return "/admin/index";

    }
    @RequestMapping("")
    public String start(){
        return "redirect:/admin/toLogin";
    }
    @RequestMapping("/login")
    public String login(@Valid LoginDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(dto.getUsername(), dto.getPassword());
        Subject subject = SecurityUtils.getSubject();
        String reason = null;
        try{
            subject.getSession().setTimeout(15 * 24 * 3600 * 1000);
            subject.login(usernamePasswordToken);
        }catch (AuthenticationException e){
            reason = "用户名无效或者密码无效";
        }
        if (reason == null) return "redirect:/admin/index";

        return "redirect:/admin/toLogin";
    }
    @RequestMapping("/toLogin")
    public String login(){
        return "/admin/login";
    }
    @RequestMapping("/top")
    @RequiresAuthentication
    public String top(HttpServletRequest request, ModelMap model){
        User user = User.getUserInfo();
        model.put("user", user);
        model.put("role", roleService.findRoleById(user.getRoleId()));
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
