package com.example.demo.order.remark.controller;

import com.example.demo.controller.BaseApiController;
import com.example.demo.data.CommonResult;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.IllegalArgumentException;
import com.example.demo.order.admin.pojo.User;
import com.example.demo.order.remark.dto.AddRemarkDto;
import com.example.demo.order.remark.dto.DeleteDto;
import com.example.demo.order.remark.service.RemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by JIAYI_TAN on 2019/3/12.
 */
@RestController
@RequestMapping("/api/admin/remark")
public class RemarkApiController extends BaseApiController {
    @Autowired
    private RemarkService remarkService;
    @RequestMapping("/delete")
    public CommonResult delete(@Valid DeleteDto dto, BindingResult bindingResult, HttpServletRequest request){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        User user = (User) request.getSession().getAttribute("user");
        if (user.getRoleId() != 2) throw new BusinessException("对不起，你当前没有此权限！");
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
