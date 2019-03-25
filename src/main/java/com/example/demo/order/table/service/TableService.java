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
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import tk.mybatis.mapper.entity.Condition;

import java.io.FileNotFoundException;
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
    public int addTable(String host, String port, String tableSn, String num) {
        Condition condition = new Condition(DiningTable.class);
        condition.createCriteria().andCondition("tablesn=", tableSn);
        List<DiningTable> list = tableMapper.selectByExample(condition);
        if (list.size() > 0) throw new BusinessException("桌子编号已经存在！");
        int result = tableMapper.insert(new DiningTable(tableSn, num, false));
        List<DiningTable> listlater = tableMapper.selectByExample(condition);
        DiningTable diningTable = listlater.get(0);
        String codePath = autoCode(host, port, tableSn);
        diningTable.setCode(codePath);
        tableMapper.updateByPrimaryKey(diningTable);
        return result;
    }
    /*
    生成桌子二维码图片
     */
    private String autoCode(String host, String port, String tableSn){
        String text = "http://"+host+":"+port+"/web?tableSn="+tableSn;
        System.out.println("ip4地址："+text);
        String path = null;
        try {
            String serverpath= ResourceUtils.getURL("classpath:static/images").getPath().replace("%20"," ").replace('/', '\\');
            System.out.println(serverpath);
            path=serverpath.substring(1);//从路径字符串中取出工程路径
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(path);
        try {
            QRCodeUtil.encode(text, "", path, tableSn, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableSn+".jpg";
    }

    public void deleteTable(int id) {
        tableMapper.deleteByPrimaryKey(id);
    }
    public void updateTableState(String tableSn, boolean state){
        DiningTable diningTable = isExist(tableSn);
        diningTable.setFlag(state);
        tableMapper.updateByPrimaryKey(diningTable);
    }

    public DiningTable isExist(String tableSn) {
        Condition condition = new Condition(DiningTable.class);
        condition.createCriteria().andCondition("tablesn=", tableSn);
        List<DiningTable> list = tableMapper.selectByExample(condition);
        if (list.size() == 0) throw new BusinessException("此餐桌不存在!");
        return list.get(0);
    }
}
