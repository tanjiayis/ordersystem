package com.example.demo.order.privilege.service;

import com.example.demo.order.privilege.mapper.PrivilegeMapper;
import com.example.demo.order.privilege.mapper.RoleMapper;
import com.example.demo.order.privilege.pojo.Role;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by JIAYI_TAN on 2019/3/22.
 */
@Service
public class PrivilegeService{
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PrivilegeMapper privilegeMapper;
    public PageInfo<Role> getRolesList(int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Role> list = roleMapper.selectAll();
        return new PageInfo<>(list);
    }
    public Set<String> getRolePermissions(int roleId) {
        Set<String> permissions = privilegeMapper.getRolePermissions(roleId);
        return permissions;
    }
}
