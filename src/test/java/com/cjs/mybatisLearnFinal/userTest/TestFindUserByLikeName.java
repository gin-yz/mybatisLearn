package com.cjs.mybatisLearnFinal.userTest;

import com.cjs.mybatisLearnFinal.dao.UserDao;
import com.cjs.mybatisLearnFinal.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestFindUserByLikeName {
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
    public void testFindUserByLikeName(){
        UserDao userDao = this.sqlSession.getMapper(UserDao.class);

        List<User> userList = userDao.findUserByNameLike("cjs");

        userList.forEach(System.out::println);
    }

    @After
    public void destroy() throws IOException {
        this.sqlSession.commit();
        this.sqlSession.close();
        this.inputStream.close();
    }
}
