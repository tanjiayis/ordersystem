package com.example.demo.order.privilege.service;

import com.example.demo.exception.BusinessException;
import com.example.demo.order.privilege.mapper.PrivilegeMapper;
import com.example.demo.order.privilege.mapper.RoleMapper;
import com.example.demo.order.privilege.mapper.RolePrivilegeMapper;
import com.example.demo.order.privilege.pojo.Privilege;
import com.example.demo.order.privilege.pojo.Role;
import com.example.demo.order.privilege.pojo.RolePrivilegeTb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.sql.BatchUpdateException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by JIAYI_TAN on 2019/3/22.
 */
@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PrivilegeMapper privilegeMapper;
    @Autowired
    private RolePrivilegeMapper rolePrivilegeMapper;
    public void add(String name) {
        Condition condition = new Condition(Role.class);
        condition.createCriteria().andCondition("name=", name);
        List<Role> list = roleMapper.selectByExample(condition);
        if (list.size() > 0) throw new BusinessException("此角色已经存在!");
        Role role = new Role(name);
        roleMapper.insert(role);
    }
    public void delete(int roleId){
        List<RolePrivilegeTb> list = roleMapper.findPer(roleId);
        if (list.size() > 0) throw new BusinessException("该角色附有权限，不可删除!");
        roleMapper.deleteByPrimaryKey(roleId);
    }

    public void updatePer(int roleId, String[] privileges) {
        Condition condition = null;
        List<RolePrivilegeTb> list = roleMapper.findPer(roleId);
        if (list.size() > 0) roleMapper.deletePer(roleId);
        RolePrivilegeTb rolePrivilegeTb = null;
        for (String privilege : privileges){
            condition = new Condition(Privilege.class);
            condition.createCriteria().andCondition("name=", privilege);
            Privilege privilege1 = privilegeMapper.selectByExample(condition).get(0);
            rolePrivilegeTb = new RolePrivilegeTb(roleId, privilege1.getId());
            rolePrivilegeMapper.insert(rolePrivilegeTb);
        }
    }

    public List<Role> findAllRole() {
        return roleMapper.selectAll();
    }

    public Role findRoleById(int id) {
        return roleMapper.selectByPrimaryKey(id);
    }
}
