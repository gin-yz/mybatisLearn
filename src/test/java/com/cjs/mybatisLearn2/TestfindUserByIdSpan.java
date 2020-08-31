package com.cjs.mybatisLearn2;

import com.cjs.mybatisLearn2.dao.UserDao;
import com.cjs.mybatisLearn2.domain.QueryPoJo;
import com.cjs.mybatisLearn2.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class TestfindUserByIdSpan {
    private SqlSession sqlSession;
    private InputStream inputStream;

    @Before
    public void init() throws IOException {
        this.inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);

        this.sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    public void findUserByIdSpan(){
        User user = new User();
        user.setUsername("cjs");

        List<Integer> list = new ArrayList<Integer>(Arrays.asList(129,130,131));

        QueryPoJo queryPoJo = new QueryPoJo();
        queryPoJo.setUser(user);
        queryPoJo.setIdList(list);

        UserDao userDao = this.sqlSession.getMapper(UserDao.class);

        List<User> userList = userDao.findUserByIdSpan(queryPoJo);
        userList.forEach(System.out::println);
    }

    @After
    public void destroy() throws IOException {
        this.sqlSession.commit();
        this.sqlSession.close();
        this.inputStream.close();
    }

}
