package com.example.demo.order.table.service;

import com.example.demo.code.QRCodeUtil;
import com.example.demo.exception.BusinessException;
import com.example.demo.order.table.mapper.TableMapper;
import com.example.demo.order.table.pojo.DiningTable;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * Created by JIAYI_TAN on 2019/2/22.
 */
@Service
public class TableService {
    @Autowired
    private TableMapper tableMapper;

    public PageInfo<DiningTable> listTable(String tableSn, String num, boolean state, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<DiningTable> list = tableMapper.listTable(tableSn, num, state);
        return new PageInfo<>(list);
    }
    @Transactional
    public int addTable(String host, String tableSn, String num) {
        Condition condition = new Condition(DiningTable.class);
        condition.createCriteria().andCondition("tablesn=", tableSn);
        List<DiningTable> list = tableMapper.selectByExample(condition);
        if (list.size() > 0) throw new BusinessException("桌子编号已经存在！");
        int result = tableMapper.insert(new DiningTable(tableSn, num, false));
        List<DiningTable> listlater = tableMapper.selectByExample(condition);
        DiningTable diningTable = listlater.get(0);
        String codePath = autoCode(host, tableSn);
        diningTable.setCode(codePath);
        tableMapper.updateByPrimaryKey(diningTable);
        return result;
    }
    /*
    生成桌子二维码图片
     */
    private String autoCode(String host, String tableSn){
//        String text = "此桌编号为:"+tableSn;
        String text = "http://"+host+":6100/web?tableSn="+tableSn;
        try {
            QRCodeUtil.encode(text, "", "/ordersystem/src/main/resources/static/images", tableSn, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableSn+".jpg";
    }

    public void deleteTable(int id) {
        DiningTable diningTable = tableMapper.selectByPrimaryKey(id);
        diningTable.setDeleted(true);
        tableMapper.updateByPrimaryKey(diningTable);
    }
}
