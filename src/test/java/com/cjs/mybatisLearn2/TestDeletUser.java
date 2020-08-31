package com.cjs.mybatisLearn2;

import com.cjs.mybatisLearn2.dao.UserDao;
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
import java.util.List;

public class TestDeletUser {
    private InputStream inputStream;
    private SqlSession sqlSession;

    @Before
    public void init() throws IOException {
        this.inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(this.inputStream);

        this.sqlSession = sqlSessionFactory.openSession();
    }

    @After
    public void destroy() throws IOException {
        this.sqlSession.commit();
        this.sqlSession.close();
        this.inputStream.close();
    }

    @Test
    public void deleteUser() {
        UserDao userDao = this.sqlSession.getMapper(UserDao.class);

        List<User> userList = userDao.findAll();

        userList.forEach(user -> {
            if(user.getUsername().length()>=4 && user.getUsername().substring(0,3).equals("cjs")){
                userDao.deleteUser(user.getId());
            }
        });

    }

}
