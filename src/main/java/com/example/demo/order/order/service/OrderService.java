package com.example.demo.order.order.service;

import com.example.demo.exception.BusinessException;
import com.example.demo.order.menu.mapper.MenuMapper;
import com.example.demo.order.menu.pojo.Menu;
import com.example.demo.order.order.mapper.OrderDetailMapper;
import com.example.demo.order.order.mapper.OrderMapper;
import com.example.demo.order.order.model.DayOrder;
import com.example.demo.order.order.model.HotMenu;
import com.example.demo.order.order.model.Menus;
import com.example.demo.order.order.model.OrderInfo;
import com.example.demo.order.table.mapper.TableMapper;
import com.example.demo.order.order.enums.OrderStateEnum;
import com.example.demo.order.order.pojo.Order;
import com.example.demo.order.order.pojo.OrderDetail;
import com.example.demo.order.table.pojo.DiningTable;
import com.example.demo.order.table.service.TableService;
import com.example.demo.redis.RedisOperator;
import com.example.demo.web.model.MyOrder;
import com.example.demo.web.model.MyOrder2;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
    private TableService tableService;
    @Autowired
    private RedisOperator redisOperator;

    /**
     * 顾客点餐以及加餐业务
     * @param tableSn
     * @param menuIds
     * @return
     */
    @Transactional
    public int addOrder(String tableSn, String menuIds) {
        tableService.updateTableState(tableSn, true);//修改餐桌是否有人的状态
        //判断此卓是否有未完成的订单，如果有则找出此订单，<有且只有一条记录>，并向其添加菜，否则就创建新的订单
        Condition condition = new Condition(Order.class);
        condition.createCriteria().andCondition("table_sn=", tableSn).andCondition("complete=", OrderStateEnum.todocompleted);
        List<Order> orders = orderMapper.selectByExample(condition);
        Order order = orders.size() > 0 ? orders.get(0):null;
        List<Integer> ids = new ArrayList<>();
        Arrays.stream(menuIds.split(",")).forEach(id -> ids.add(Integer.parseInt(id)));
        BigDecimal totalPrice = new BigDecimal(BigInteger.ZERO);
        for (int id : ids){
            totalPrice = totalPrice.add(menuMapper.selectByPrimaryKey(id).getPrice());
        }
        int result = order == null?addOrderT(tableSn, menuIds,ids, totalPrice):updateOrderT(order, menuIds, totalPrice);
        if (result > 0)  setSubMenuTime(result, menuIds);
        return result;
    }

    private void setSubMenuTime(int orderId, String menuIds ){
        String key = String.valueOf(orderId);
        redisOperator.set(key, menuIds, 5*60);
    }
    @Transactional
    private int addOrderT(String tableSn, String menuIds,List<Integer> ids, BigDecimal totalPrice){
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
        return orderId;
    }
    @Transactional
    private int updateOrderT(Order order, String menuIds, BigDecimal totalPrice){
        List<String> ids = new ArrayList<String>();
        Arrays.stream((order.getMenuId() +","+ menuIds).split(",")).forEach(id -> {
            if (!ids.contains(id)) { ids.add(id);}
        });
        String[] newmenuIds = ids.toArray(new String[ids.size()]);
        StringBuffer stringBuffer = new StringBuffer();
        Arrays.stream(newmenuIds).forEach(newid -> stringBuffer.append(newid).append(","));
        String menuIdsResult = stringBuffer.toString();
        String menuId = menuIdsResult.substring(0, menuIdsResult.length()-1);
        order.setMenuId(menuId);
        order.setTotalPrice(order.getTotalPrice().add(totalPrice));
        int result = orderMapper.updateByPrimaryKey(order);
        Condition condition = new Condition(OrderDetail.class);
        condition.createCriteria().andCondition("order_id=", order.getId());
        List<OrderDetail> details = detailMapper.selectByExample(condition);
        List<Integer> listmenuId = new ArrayList<>();
        List<Integer> newMM = null;
        Arrays.stream(menuIds.split(",")).forEach(id -> listmenuId.add(Integer.parseInt(id)));
        System.out.println(listmenuId);
        List<OrderDetail> contain = null;
        contain = details.stream()
                .filter((OrderDetail o) -> listmenuId.contains(o.getMenuId()))
                .collect(Collectors.toList());
        if (contain != null){
            newMM = new ArrayList<>();
            List<Integer> ll = new ArrayList<>();
            contain.forEach(cc -> ll.add(cc.getMenuId()));
            for (Integer ii : listmenuId){
                if (!ll.contains(ii) && !newMM.contains(ii)){newMM.add(ii);}
            }
        }
        newMM = contain == null?listmenuId:newMM;
        if (contain != null) contain.forEach(e -> {e.setMenuNum(e.getMenuNum()+1);detailMapper.updateDetail(e);});
        if (newMM != null) newMM.forEach(f -> {OrderDetail orderDetail = new OrderDetail(order.getId(), f, 1);detailMapper.insert(orderDetail);});
        return order.getId();
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
    @Transactional
    public String updateState(int orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        OrderStateEnum orderStateEnum = order.getComplete();
        OrderStateEnum newState = orderStateEnum==OrderStateEnum.completed ? OrderStateEnum.todocompleted:OrderStateEnum.completed;
        order.setComplete(newState);
        boolean orderState = orderStateEnum==OrderStateEnum.completed ?true:false;
        tableService.updateTableState(order.getTableSn(), orderState);
        int result = orderMapper.updateByPrimaryKey(order);
        return result > 0 ? order.getComplete().getCode():orderStateEnum.getCode();
    }

    public List<MyOrder> findMyOrder(String tableSn) {
        List<MyOrder> list = orderMapper.findMyOrder(tableSn);
        return list;
    }

    public Order findMyOrder2(String tableSn) {
        Condition condition = new Condition(Order.class);
        condition.createCriteria().andCondition("table_sn=", tableSn).andCondition("complete=", OrderStateEnum.todocompleted);
        List<Order> orders = orderMapper.selectByExample(condition);
        Order order = orders.size()>0?orders.get(0):null;
        return order;
    }

    /**
     * 顾客退餐业务，点餐后五分钟之内可退餐
     * @param menuId
     * @param orderId
     */
    public void deleteMenuByOrderId(int menuId, int orderId) {
        String value = redisOperator.get(String.valueOf(orderId));
        if (value == null) throw new BusinessException("超过退餐所限时间!");
        Condition condition = new Condition(OrderDetail.class);
        condition.createCriteria().andCondition("order_id=", orderId).andCondition("menu_id=", menuId);
        detailMapper.deleteByExample(condition);
    }

    public List<HotMenu> findHot(int num) {
        List<HotMenu> list = orderMapper.findHot(num);
        return list;
    }
}
