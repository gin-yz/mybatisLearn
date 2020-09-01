package com.cjs.mybatisLearnFinal.dao;

import com.cjs.mybatisLearnFinal.domain.Role;

import java.util.List;

public interface RoleDao {

    List<Role> findRoleAll();

    //多对多查询时，通常指定一个外键进入(如user和role)，再通过user和role中定义List字段传回
    List<Role> findRoleManyToManyWithUsers();

    List<Role> findRoleManyToManyWithUsersAndLazy();

}
