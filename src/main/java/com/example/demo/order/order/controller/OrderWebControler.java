package com.example.demo.order.order.controller;

import com.example.demo.controller.BaseController;
import com.example.demo.data.CommonPage;
import com.example.demo.order.order.dto.DetailDto;
import com.example.demo.order.order.dto.FilterOrderDto;
import com.example.demo.exception.IllegalArgumentException;
import com.example.demo.order.order.model.DayOrder;
import com.example.demo.order.order.model.OrderInfo;
import com.example.demo.order.order.pojo.Order;
import com.example.demo.order.order.service.OrderService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by JIAYI_TAN on 2019/3/5.
 */
@Controller
@RequestMapping("/admin/order")
public class OrderWebControler extends BaseController{
    @Autowired
    private OrderService orderService;
    @RequestMapping("/list")
    public String listOrder(@Valid FilterOrderDto dto, Model model, CommonPage page, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        PageInfo<Order> list = orderService.listOrder(dto.getTableSn(), dto.getStartTime(), dto.getEndTime(), dto.getComplete(), page.getPageIndex(), page.getPageSize());
        model.addAttribute("orders", list.getList());
        model.addAttribute("param", dto.wrapAsMap());
        model.addAttribute("page", new CommonPage(list));
        model.addAttribute("count", list.getTotal());
        return "/admin/order/order-list";
    }
    @RequestMapping("/report")
    public String Report(ModelMap model){
//        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        List<DayOrder> list  = orderService.findDayOrdedr();
        model.put("dayOrder", list);
        return "/admin/order/order-report";
    }
    @RequestMapping("/detail")
    public String detail(@Valid DetailDto dto,  ModelMap model, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        OrderInfo  orderInfo =  orderService.orderDetail(dto.getId());
        model.put("orderInfo", orderInfo);
        return "/admin/order/order-detail";
    }
}
