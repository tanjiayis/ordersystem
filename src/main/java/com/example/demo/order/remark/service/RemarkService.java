package com.example.demo.order.remark.service;

import com.example.demo.exception.BusinessException;
import com.example.demo.order.remark.mapper.RemarkMapper;
import com.example.demo.order.remark.pojo.Message;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by JIAYI_TAN on 2019/3/12.
 */
@Service
public class RemarkService {
    @Autowired
    private RemarkMapper remarkMapper;
    public PageInfo<Message> listRemark(String startTime, String endTime, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        Condition condition = new Condition(Message.class);
        Example.Criteria criteria = condition.createCriteria();
        if (StringUtil.isNotEmpty(startTime)) criteria.andCondition("create_time >", startTime);
        if (StringUtil.isNotEmpty(endTime)) criteria.andCondition("create_time <=", endTime);
        condition.setOrderByClause("create_time desc");
        List<Message> list =  remarkMapper.selectByExample(condition);
        return new PageInfo<>(list);
    }

    public void deleteRemark(int id) {
        if (id == 0) throw new BusinessException("不能为空");
        remarkMapper.deleteByPrimaryKey(id);
    }

    public int addRemark(String content) {
        Message message = new Message(new Date(), content);
        return remarkMapper.insert(message);
    }

    public List<Message> listRemark() {
        Condition condition = new Condition(Message.class);
        condition.setOrderByClause("create_time desc");
        return remarkMapper.selectByExample(condition);
    }
}
