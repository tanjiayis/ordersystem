package com.example.demo.order.remark.controller;

import com.example.demo.controller.BaseApiController;
import com.example.demo.data.CommonResult;
import com.example.demo.exception.IllegalArgumentException;
import com.example.demo.order.remark.dto.AddRemarkDto;
import com.example.demo.order.remark.dto.DeleteDto;
import com.example.demo.order.remark.service.RemarkService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by JIAYI_TAN on 2019/3/12.
 */
@RestController
@RequestMapping("/api/admin/remark")
public class RemarkApiController extends BaseApiController {
    @Autowired
    private RemarkService remarkService;
    @RequiresPermissions("remark_man.del")
    @RequestMapping("/delete")
    public CommonResult delete(@Valid DeleteDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        remarkService.deleteRemark(dto.getId());
        return new CommonResult();
    }
    @RequestMapping("/add")
    public CommonResult add(@Valid AddRemarkDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        int result = remarkService.addRemark(dto.getContent());
        return new CommonResult(result);
    }
}
