package com.cjs.mybatisLearnFinal;

import com.cjs.mybatisLearnfinal.dao.UserDao;
import com.cjs.mybatisLearnfinal.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestUpdateUser {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);

        List<User> userList = userDao.findAll();

//        for (User user:userList){
//            if (user.getId()>=59){
//                user.setUsername("cjsdsg"+user.getUsername().substring(3));
//                userDao.updateUser(user);
//            }
//        }
        userList.forEach(user ->{
            if(user.getId()>=59){
                user.setUsername("cjs"+user.getUsername().substring(6));
                userDao.updateUser(user);
            }
        });


        sqlSession.commit();
        sqlSession.close();
        inputStream.close();
    }
}
