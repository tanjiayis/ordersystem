package com.example.demo.web.controller;

import com.example.demo.order.menu.service.MenuService;
import com.example.demo.order.order.enums.OrderStateEnum;
import com.example.demo.order.order.service.OrderService;
import com.example.demo.web.model.MyOrder;
import com.example.demo.web.model.MyOrder2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by JIAYI_TAN on 2019/3/5.
 */
@Controller
@RequestMapping("")
public class IndexController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private OrderService orderService;
    @RequestMapping("/web")
    public String selectFoods(@RequestParam("tableSn") String tableSn, ModelMap model){
        model.put("types", menuService.findAllType());
        model.put("typeAndMenus", menuService.findAll());
        model.put("tableSn", tableSn);
        return "/web/index";
    }
    @RequestMapping("/web/my_order")
    public String myCurrentOrder(@RequestParam("tableSn") String tableSn, ModelMap model){
        List<MyOrder> list = orderService.findMyOrder(tableSn);
        MyOrder2 myOrder2 = orderService.findMyOrder2(tableSn);
        model.put("order_menus", list);
        model.put("myOrder2", myOrder2);
        model.put("tableSn", tableSn);
;        return "/web/my-order";
    }
}
