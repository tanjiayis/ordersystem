package com.example.demo.order.menu.service;

import com.example.demo.exception.BusinessException;
import com.example.demo.order.menu.mapper.MenuMapper;
import com.example.demo.order.menu.mapper.MenuTypeMapper;
import com.example.demo.order.menu.model.MenuTree;
import com.example.demo.order.menu.pojo.Menu;
import com.example.demo.order.menu.pojo.MenuType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JIAYI_TAN on 2019/2/25.
 */
@Service
public class MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private MenuTypeMapper menuTypeMapper;

    @Transactional
    public List<MenuTree> findAll() {
        List<MenuTree> list = new ArrayList<>();
        List<MenuType> types = menuTypeMapper.selectAll();
        types.stream().forEach(type -> {
            Condition condition = new Condition(Menu.class);
            condition.createCriteria().andCondition("typeid=",  type.getId());
            List<Menu> menus = menuMapper.selectByExample(condition);
            MenuTree menuTree = new MenuTree(type.getId(), type.getTypeName(), menus);
            list.add(menuTree);
        });
        return list;
    }

    public List<MenuType> findAllType(String name) {
        Condition condition = new Condition(MenuType.class);
        if (StringUtil.isNotEmpty(name)) condition.createCriteria().andLike("typeName", "%"+name+"%");
        List<MenuType> list = menuTypeMapper.selectByExample(condition);
        return list;
    }

    public PageInfo<Menu> findAllMenus(String name, int typeId, boolean state, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        Condition condition = new Condition(Menu.class);
        Example.Criteria criteria = condition.createCriteria();
        if (StringUtil.isNotEmpty(name)) criteria.andLike("name", "%"+name+"%");
        if (typeId != 0) criteria.andCondition("typeid=", typeId);
        if (state) criteria.andCondition("state=", true);
        List<Menu> list = menuMapper.selectByExample(condition);
        return new PageInfo<>(list);
    }

    public void addMenu(int typeId, String name, BigDecimal price, String remark) {
        Menu menu = new Menu(typeId, name, price, null, remark, true);
        menuMapper.insert(menu);
    }

    public Menu findMenu(int id) {
        Menu menu = menuMapper.selectByPrimaryKey(id);
        return  menu;
    }

    public void editMenu(int menuId, int typeId, String name, BigDecimal price, String remark) {
        Menu menu = menuMapper.selectByPrimaryKey(menuId);
        if (menu == null) throw new BusinessException("不存在此菜品!");
        menu.setTypeId(typeId);
        menu.setName(name);
        menu.setPrice(price);
        menu.setRemark(remark);
        menuMapper.updateByPrimaryKey(menu);
    }

    public void addType(String typeName) {
        if (StringUtil.isEmpty(typeName)) throw new BusinessException("分类名称不能为空");
        MenuType type = new MenuType();
        type.setTypeName(typeName);
        menuTypeMapper.insert(type);
    }

    public List<Menu> findTypeAllMenus(int typeId) {
        if (typeId == 0) throw new BusinessException("类别Id不能为0!");
        Condition condition = new Condition(Menu.class);
        condition.createCriteria().andCondition("typeid=", typeId);
        return menuMapper.selectByExample(condition);
    }

    public MenuType findType(int typeId) {
        return menuTypeMapper.selectByPrimaryKey(typeId);
    }

    public void editType(int typeId, String typeName) {
        MenuType menuType = findType(typeId);
        if (menuType == null) throw new BusinessException("不存在此类别!");
        menuType.setTypeName(typeName);
        menuTypeMapper.updateByPrimaryKey(menuType);
    }

    public void deleteType(int typeId) {
        List<Menu> menus = findTypeAllMenus(typeId);
        if (menus.size() > 0) throw new BusinessException("此类别下有菜品，不可删除!");
        menuTypeMapper.deleteByPrimaryKey(typeId);
    }

    public void deleteMenu(int menuId) {
        menuMapper.deleteByPrimaryKey(menuId);
    }
}
