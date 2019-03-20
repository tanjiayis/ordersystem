package com.example.demo.order.order.service;

import com.example.demo.exception.BusinessException;
import com.example.demo.order.menu.mapper.MenuMapper;
import com.example.demo.order.menu.pojo.Menu;
import com.example.demo.order.order.mapper.OrderDetailMapper;
import com.example.demo.order.order.mapper.OrderMapper;
import com.example.demo.order.order.model.DayOrder;
import com.example.demo.order.order.model.Menus;
import com.example.demo.order.order.model.OrderInfo;
import com.example.demo.order.table.mapper.TableMapper;
import com.example.demo.order.order.enums.OrderStateEnum;
import com.example.demo.order.order.pojo.Order;
import com.example.demo.order.order.pojo.OrderDetail;
import com.example.demo.order.table.pojo.DiningTable;
import com.example.demo.web.model.MyOrder;
import com.example.demo.web.model.MyOrder2;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by JIAYI_TAN on 2019/3/5.
 */
@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private OrderDetailMapper detailMapper;
    @Autowired
    private TableMapper tableMapper;

    @Transactional
    public void addOrder(String tableSn, String menuIds) {
        Condition condition = new Condition(DiningTable.class);
        condition.createCriteria().andCondition("tablesn=", tableSn);
        List<DiningTable> tables = tableMapper.selectByExample(condition);
        tables.get(0).setFlag(true);
        tableMapper.updateByPrimaryKey(tables.get(0));
        List<Integer> ids = new ArrayList<>();
        Arrays.stream(menuIds.split(",")).forEach(id -> ids.add(Integer.parseInt(id)));
        BigDecimal totalPrice = new BigDecimal(BigInteger.ZERO);
        for (int id : ids){
            totalPrice = totalPrice.add(menuMapper.selectByPrimaryKey(id).getPrice());
        }
        Date date = new Date();
        Order order = new Order(tableSn, menuIds, totalPrice, date, OrderStateEnum.todocompleted);
        int key = orderMapper.insertOrder(order);
        int orderId = 0;
        if (key >0){
            Order order1 = orderMapper.selectByPrimaryKey(order.getId());
            orderId = order1.getId();
        }
        for (int menuId: ids){
            OrderDetail detail = new OrderDetail(orderId, menuId, 1);
            detailMapper.insert(detail);
        }
    }

    public PageInfo<Order> listOrder(String tableSn, String startTime, String endTime, String complete, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        Condition condition = new Condition(Order.class);
        Example.Criteria criteria = condition.createCriteria();
        if (StringUtil.isNotEmpty(tableSn)) criteria.andCondition("table_sn=", tableSn);
        if (StringUtil.isNotEmpty(startTime)) criteria.andCondition("create_time >", startTime);
        if (StringUtil.isNotEmpty(endTime)) criteria.andCondition("create_time <=", endTime);
        if (!complete.equals("all")) criteria.andCondition("complete=", complete);
        condition.setOrderByClause("create_time desc");
        List<Order>  list = orderMapper.selectByExample(condition);
        return new PageInfo<>(list);
    }

    @Transactional
    public OrderInfo orderDetail(int id) {
        List<Menus> menusList = new ArrayList<>();
        Order order = orderMapper.selectByPrimaryKey(id);
        Condition condition = new Condition(OrderDetail.class);
        condition.createCriteria().andCondition("order_id=", id);
        List<OrderDetail> list = detailMapper.selectByExample(condition);
        list.stream().forEach(rec -> {
            Menu menu = menuMapper.selectByPrimaryKey(rec.getMenuId());
            Menus menus = new Menus(rec.getMenuNum(), menu);
            menusList.add(menus);
        });
        OrderInfo orderInfo = new OrderInfo(order.getId(), order.getCreateTime(), order.getTableSn(), order.getTotalPrice(), order.getMenuId(), menusList);
        return orderInfo;
    }

    public List<DayOrder> findDayOrdedr() {
        return orderMapper.findDayOrders();
    }

    public void deleteOrder(int orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (order.getComplete().equals(OrderStateEnum.todocompleted)) throw new BusinessException("此订单未完成，不能删除!");
        orderMapper.deleteByPrimaryKey(orderId);
    }
/*
修改订单状态
 */
    public String updateState(int orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        OrderStateEnum orderStateEnum = order.getComplete();
        OrderStateEnum newState = orderStateEnum==OrderStateEnum.completed ? OrderStateEnum.todocompleted:OrderStateEnum.completed;
        order.setComplete(newState);
        int result = orderMapper.updateByPrimaryKey(order);
        return result > 0 ? order.getComplete().getCode():orderStateEnum.getCode();
    }

    public List<MyOrder> findMyOrder(String tableSn) {
        List<MyOrder> list = orderMapper.findMyOrder(tableSn);
        return list;
    }

    public MyOrder2 findMyOrder2(String tableSn) {
        Condition condition = new Condition(Order.class);
        condition.createCriteria().andCondition("table_sn=", tableSn).andCondition("complete=", OrderStateEnum.todocompleted);
        List<Order> orders = orderMapper.selectByExample(condition);
        Order order = orders.size()>0?orders.get(0):null;
        MyOrder2 myOrder2 = order == null?null:new MyOrder2(order.getCreateTime(), order.getTotalPrice());
        return myOrder2;
    }
}
