package com.example.demo.order.remark.controller;

import com.example.demo.controller.BaseController;
import com.example.demo.data.CommonPage;
import com.example.demo.exception.IllegalArgumentException;
import com.example.demo.order.remark.dto.FilterRemarkDto;
import com.example.demo.order.remark.pojo.Message;
import com.example.demo.order.remark.service.RemarkService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.net.BindException;
import java.util.List;

/**
 * Created by JIAYI_TAN on 2019/3/12.
 */
@Controller
@RequestMapping("/admin/remark")
public class RemarkWebController extends BaseController {
    @Autowired
    private RemarkService remarkService;
    @RequestMapping("/list")
    public String list(@Valid FilterRemarkDto dto, ModelMap model, BindingResult bindingResult, CommonPage page){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        PageInfo<Message> list = remarkService.listRemark(dto.getStartTime(), dto.getEndTime(), page.getPageIndex(), page.getPageSize());
        model.put("remarks", list.getList());
        model.put("page", new CommonPage(list));
        model.put("param", dto.wrapAsMap());
        return "/admin/remark/remark-list";
    }
    @RequestMapping("/web")
    public String webList(@RequestParam("tableSn") String tableSn, ModelMap model){
        List<Message> list = remarkService.listRemark();
        model.put("remarks", list);
        model.put("count", list.size());
        model.put("tableSn", tableSn);
        return "/web/remark";
    }
}
