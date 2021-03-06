package com.cjs.mybatisLearnFinal.dao;

import com.cjs.mybatisLearnFinal.domain.QueryPoJo;
import com.cjs.mybatisLearnFinal.domain.User;

import java.util.List;

public interface UserDao {

    List<User> findUserAll();

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(Integer userid);

    List<User> findUserByNameLike(String username); //模糊查询

    List<User> findUserByPoJo(QueryPoJo pojo);  //包装类里面嵌套包装类访问

    List<User> findUserByCondition(User user);  //使用if条件查询

    List<User> findUserByIdSpan(QueryPoJo queryPoJo); //sql语句中使用in进行查询，需要传入多个值，且多个值事先不知道传入多少个

    List<User> findUserWithLotsAccountAll(); //一对多关系查询

    List<User> findUserByRoleId();//多对多中的延迟查询

}
