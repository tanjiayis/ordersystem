package com.example.demo.order.menu.controller;

import com.example.demo.controller.BaseApiController;
import com.example.demo.data.CommonResult;
import com.example.demo.order.menu.dto.AddMenuDto;
import com.example.demo.order.menu.dto.AddTypeDto;
import com.example.demo.order.menu.dto.DetailDto;
import com.example.demo.order.menu.pojo.Menu;
import com.example.demo.order.menu.pojo.MenuType;
import com.example.demo.exception.IllegalArgumentException;
import com.example.demo.order.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by JIAYI_TAN on 2019/3/5.
 */
@RestController
@RequestMapping("/api/admin/menu")
public class MenuApiController extends BaseApiController {

    @Autowired
    private MenuService menuService;
    @RequestMapping("/add")
    public CommonResult addMenu(@Valid AddMenuDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        menuService.addMenu(dto.getTypeId(), dto.getName(), dto.getPrice(), dto.getRemark());
        return new CommonResult();
    }
    @RequestMapping("/detail")
    public CommonResult detail(@Valid DetailDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        Menu menu = menuService.findMenu(dto.getId());
        return new CommonResult(menu);
    }
    @RequestMapping("/edit")
    public CommonResult editMenu(@Valid AddMenuDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        menuService.editMenu(dto.getMenuId(), dto.getTypeId(), dto.getName(), dto.getPrice(), dto.getRemark());
        return new CommonResult();
    }
    @RequestMapping("/delete")
    public CommonResult deleteMenu(@Valid DetailDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        menuService.deleteMenu(dto.getId());
        return new CommonResult();
    }
    @RequestMapping("/type/detail")
    public CommonResult detailType(@Valid DetailDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        MenuType menuType = menuService.findType(dto.getId());
        return new CommonResult(menuType);
    }
    @RequestMapping("/type/add")
    public CommonResult addType(@Valid AddTypeDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        menuService.addType(dto.getTypeName());
        return new CommonResult();
    }
    @RequestMapping("/type/edit")
    public CommonResult editType(@Valid AddTypeDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        menuService.editType(dto.getId(), dto.getTypeName());
        return new CommonResult();
    }
    @RequestMapping("/type/delete")
    public CommonResult deleteType(@Valid DetailDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        menuService.deleteType(dto.getId());
        return new CommonResult();
    }
}
