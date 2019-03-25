package com.example.demo.order.order.mapper;

import com.example.demo.order.order.pojo.OrderDetail;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by JIAYI_TAN on 2019/3/5.
 */
public interface OrderDetailMapper extends Mapper<OrderDetail> {
    void updateDetail(@Param("detail") OrderDetail e);
}
