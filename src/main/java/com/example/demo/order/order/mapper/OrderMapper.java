package com.example.demo.order.order.mapper;

import com.example.demo.order.order.model.DayOrder;
import com.example.demo.order.order.pojo.Order;
import com.example.demo.web.model.MyOrder;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by JIAYI_TAN on 2019/3/5.
 */
public interface OrderMapper extends Mapper<Order>{
    List<DayOrder> findDayOrders();
    @Options(useGeneratedKeys=true, keyColumn = "id", keyProperty="id")
    int insertOrder(@Param("order") Order order);

    List<MyOrder> findMyOrder(@Param("tableSn") String tableSn);
}
