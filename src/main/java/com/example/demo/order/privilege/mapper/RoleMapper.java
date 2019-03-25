package com.example.demo.order.privilege.mapper;

import com.example.demo.order.privilege.pojo.Role;
import com.example.demo.order.privilege.pojo.RolePrivilegeTb;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by JIAYI_TAN on 2019/2/22.
 */
public interface RoleMapper extends Mapper<Role> {
    void deletePer(@Param("roleId") int roleId);

    List<RolePrivilegeTb> findPer(@Param("roleId")int roleId);
}
