package com.example.demo.order.table.controller;

import com.example.demo.controller.BaseController;
import com.example.demo.data.CommonPage;
import com.example.demo.order.table.dto.FilterTableDto;
import com.example.demo.order.table.pojo.DiningTable;
import com.example.demo.order.table.service.TableService;
import com.example.demo.exception.IllegalArgumentException;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Created by JIAYI_TAN on 2019/2/22.
 */
@Controller
@RequestMapping("/admin/table")
public class TableWebController extends BaseController{
    @Autowired
    private TableService tableService;
    @RequestMapping("/list")
    public String listTable(@Valid FilterTableDto dto, CommonPage page, ModelMap model, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        PageInfo<DiningTable> list = tableService.listTable(dto.getTableSn(), dto.getNum(), dto.isState(), page.getPageIndex(), page.getPageSize());
        model.put("tables", list.getList());
        model.put("param", dto.wrapAsMap());
        model.put("count", list.getTotal());
        model.put("page", new CommonPage(list));
        return "/admin/table/table-list";
    }

}
