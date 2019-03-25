package com.example.demo.order.privilege.mapper;

import com.example.demo.order.privilege.pojo.Privilege;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.Set;

/**
 * Created by JIAYI_TAN on 2019/2/22.
 */
public interface PrivilegeMapper extends Mapper<Privilege> {
    Set<String> getRolePermissions(@Param("roleId") int roleId);
}
