package com.example.demo.web.controller;

import com.example.demo.controller.BaseApiController;
import com.example.demo.data.CommonResult;
import com.example.demo.exception.IllegalArgumentException;
import com.example.demo.order.order.service.OrderService;
import com.example.demo.web.dto.SubMenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by JIAYI_TAN on 2019/3/23.
 */
@RestController
@RequestMapping("/api/web")
public class IndexApiController extends BaseApiController {
    @Autowired
    private OrderService orderService;
    @RequestMapping("/sub")
    public CommonResult subMenu(@Valid SubMenuDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        orderService.deleteMenuByOrderId(dto.getMenuId(), dto.getOrderId());
        return new CommonResult();
    }
}
