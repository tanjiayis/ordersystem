package com.example.demo.order.table.controller;

import com.example.demo.controller.BaseApiController;
import com.example.demo.data.CommonResult;
import com.example.demo.order.table.dto.DeleteDto;
import com.example.demo.exception.IllegalArgumentException;
import com.example.demo.order.table.dto.AddTableDto;
import com.example.demo.order.table.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * Created by JIAYI_TAN on 2019/2/25.
 */
@RestController
@RequestMapping("/api/admin/table")
public class TableApiController extends BaseApiController {
    @Autowired
    private TableService tableService;
//    添加餐桌
    @RequestMapping("/add")
    public CommonResult addTable(@Valid AddTableDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        String host = "";
        try {
            host = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        int result = tableService.addTable(host, dto.getTableSn(), dto.getNum());
        return new CommonResult(result);
    }
    @RequestMapping("/delete")
    public CommonResult delete(@Valid DeleteDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        tableService.deleteTable(dto.getId());
        return new CommonResult();
    }
}
