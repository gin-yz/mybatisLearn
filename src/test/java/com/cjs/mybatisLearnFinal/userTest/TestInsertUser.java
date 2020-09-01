package com.cjs.mybatisLearnFinal.userTest;

import com.cjs.mybatisLearnFinal.dao.UserDao;
import com.cjs.mybatisLearnFinal.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import static java.lang.String.format;

public class TestInsertUser {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);


        for (int n = 0; n < 10; n++) {
            User user = new User();
            user.setAddress(format("address is %s",n));

            Calendar calendar =Calendar.getInstance();
            calendar.set(1998, 4,28,12,30,10);
            user.setBirthday(calendar.getTime());
            user.setSex('M');
            user.setUsername("cjs"+n);

            userDao.saveUser(user);

            System.out.println(user.getId());

        }

        sqlSession.commit();

        sqlSession.close();
        inputStream.close();
    }
}
