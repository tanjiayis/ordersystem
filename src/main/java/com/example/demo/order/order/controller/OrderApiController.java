package com.example.demo.order.order.controller;

import com.example.demo.controller.BaseApiController;
import com.example.demo.data.CommonPage;
import com.example.demo.data.CommonResult;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.IllegalArgumentException;
import com.example.demo.order.order.dto.AddOrderDto;
import com.example.demo.order.order.dto.DetailDto;
import com.example.demo.order.order.dto.HotSortDto;
import com.example.demo.order.order.model.DayOrder;
import com.example.demo.order.order.model.HotMenu;
import com.example.demo.order.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

/**
 * Created by JIAYI_TAN on 2019/3/12.
 */
@RestController
@RequestMapping("/api/admin/order")
public class OrderApiController extends BaseApiController {
    @Autowired
    private OrderService orderService;
    @RequestMapping("/add")
    public CommonResult addOrder(@Valid AddOrderDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        if (dto.getMenuIds() == null || dto.getMenuIds() == "") throw new BusinessException("菜单条目不能为空!");
        int result = orderService.addOrder(dto.getTableSn(), dto.getMenuIds());
        return new CommonResult(result);
    }
    @RequestMapping("/delete")
    public CommonResult deleteOrder(@Valid DetailDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        orderService.deleteOrder(dto.getId());
        return new CommonResult();
    }
    @RequestMapping("/update/state")
    public CommonResult updateState(@Valid DetailDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        String state = orderService.updateState(dto.getId());
        return new CommonResult(state);
    }
    @RequestMapping("/dayorder")
    public CommonResult report(){
        List<DayOrder> list  = orderService.findDayOrdedr();
        Collections.reverse(list);
        return new CommonResult(list);
    }
    @RequestMapping("/hot")
    public CommonResult hot(@Valid HotSortDto dto){
        List<HotMenu> list = orderService.findHot(dto.getNum());
        return new CommonResult(list);
    }
}
