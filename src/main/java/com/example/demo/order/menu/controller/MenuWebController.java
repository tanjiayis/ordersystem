package com.example.demo.order.menu.controller;

import com.example.demo.controller.BaseController;
import com.example.demo.data.CommonPage;
import com.example.demo.order.menu.dto.DetailDto;
import com.example.demo.order.menu.dto.FilterMenuDto;
import com.example.demo.exception.IllegalArgumentException;
import com.example.demo.order.menu.dto.FilterTypeDto;
import com.example.demo.order.menu.model.MenuTree;
import com.example.demo.order.menu.pojo.Menu;
import com.example.demo.order.menu.pojo.MenuType;
import com.example.demo.order.menu.service.MenuService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by JIAYI_TAN on 2019/2/25.
 */
@Controller
@RequestMapping("/menu")
public class MenuWebController extends BaseController{
    @Autowired
    private MenuService menuService;

//    菜品分类列表展示
    @RequestMapping("/list/tree")
    public String listTreeMenu(ModelMap model){
        List<MenuTree> listTree = menuService.findAll();
        model.put("menus", listTree);
        model.put("types", menuService.findAllType(""));
        model.put("count", listTree.size());
        return "/admin/menu-list";
    }
    @RequestMapping("/category/list")
    public String categoryMenu(@Valid FilterTypeDto dto, ModelMap model){
        List<MenuType> list = menuService.findAllType(dto.getTypeName());
        model.put("types", list);
        model.put("param", dto.wrapAsMap());
        model.put("count", list.size());
        return "/admin/menu/menu-type";
    }
    @RequestMapping("/list")
    public String listMenu(@Valid FilterMenuDto dto, ModelMap model, CommonPage page, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        PageInfo<Menu> list = menuService.findAllMenus(dto.getName(), dto.getType(), dto.isState(), page.getPageIndex(), page.getPageSize());
        model.put("menus", list.getList());
        model.put("types", menuService.findAllType(""));
        model.put("param", dto.wrapAsMap());
        model.put("count", list.getTotal());
        model.put("page", new CommonPage(list));
        return "/admin/menu/menu-list";
    }
    @RequestMapping("/type")
    public String findTypeAllMenus(@Valid DetailDto dto, ModelMap model, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        List<Menu> list = menuService.findTypeAllMenus(dto.getId());
        model.put("menus", list);
        model.put("type", menuService.findType(dto.getId()));
        return "/admin/menu/type-menus";
    }
}
