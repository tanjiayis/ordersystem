package com.example.demo.order.table.mapper;

import com.example.demo.order.table.pojo.DiningTable;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by JIAYI_TAN on 2019/2/22.
 */
public interface TableMapper extends Mapper<DiningTable> {
    List<DiningTable> listTable(@Param("tableSn") String tableSn, @Param("num") String num, @Param("state") boolean state);
}
