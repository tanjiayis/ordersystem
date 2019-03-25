package com.example.demo.order.privilege.controller;

import com.example.demo.controller.BaseController;
import com.example.demo.data.CommonPage;
import com.example.demo.exception.IllegalArgumentException;
import com.example.demo.order.admin.menu.MenuItem;
import com.example.demo.order.admin.menu.MenuLoader;
import com.example.demo.order.privilege.service.RoleService;
import com.example.demo.order.user.service.UserService;
import com.example.demo.order.privilege.dto.DeleteRoleDto;
import com.example.demo.order.user.dto.FilterUserDto;
import com.example.demo.order.privilege.pojo.Role;
import com.example.demo.order.privilege.service.PrivilegeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * Created by JIAYI_TAN on 2019/3/22.
 */
@Controller
@RequestMapping("/privilege")
public class PrivilegeWebController extends BaseController{
    @Autowired
    private PrivilegeService privilegeService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @RequestMapping("/roles")
    public String roleList(ModelMap  model, CommonPage page){
        PageInfo<Role> list = privilegeService.getRolesList(page.getPageIndex(), page.getPageSize());
        model.put("roles", list.getList());
        model.put("page", new CommonPage(list));
        return "/admin/privilege/roles";
    }
    @RequestMapping("/permissions")
    public String permissionList(@Valid DeleteRoleDto dto, ModelMap model, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        List<MenuItem> menuItems = MenuLoader.getInstance().getAllMenus();
        Set<String> permissions = privilegeService.getRolePermissions(dto.getId());
        model.put("permissions", permissions);
        model.put("menuList", menuItems);
        model.put("role", roleService.findRoleById(dto.getId()));
        return "/admin/privilege/permissions";
    }
}
