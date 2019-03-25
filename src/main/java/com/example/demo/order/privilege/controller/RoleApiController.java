package com.example.demo.order.privilege.controller;

import com.example.demo.controller.BaseApiController;
import com.example.demo.data.CommonResult;
import com.example.demo.exception.IllegalArgumentException;
import com.example.demo.order.privilege.dto.AddRoleDto;
import com.example.demo.order.privilege.dto.DeleteRoleDto;
import com.example.demo.order.privilege.dto.SetPermissionDto;
import com.example.demo.order.privilege.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by JIAYI_TAN on 2019/3/22.
 */
@RestController
@RequestMapping("/api/role")
public class RoleApiController extends BaseApiController{
    @Autowired
    private RoleService roleService;
    @RequestMapping("/delete")
    public CommonResult delete(@Valid DeleteRoleDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        roleService.delete(dto.getId());
        return new CommonResult();
    }
    @RequestMapping("/add")
    public CommonResult add(@Valid AddRoleDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        roleService.add(dto.getName());
        return new CommonResult();
    }
    @RequestMapping("/privilege/set")
    public CommonResult setPer(@Valid SetPermissionDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        roleService.updatePer(dto.getRoleId(), dto.getPrivileges());
        return new CommonResult();
    }
}
