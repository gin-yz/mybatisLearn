package com.cjs.mybatisLearnFinal.roleTest;

import com.cjs.mybatisLearnFinal.dao.RoleDao;
import com.cjs.mybatisLearnFinal.domain.Role;
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

public class TestFindRoleManyToManyWithUsers {
    private InputStream inputStream;
    private SqlSession sqlSession;

    @Before
    public void init() throws IOException {
        this.inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(this.inputStream);

        this.sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    public void fndRoleManyToManyWithUsers(){
        RoleDao roleDao = this.sqlSession.getMapper(RoleDao.class);

        List<Role> roleList = roleDao.findRoleManyToManyWithUsers();

        roleList.forEach(System.out::println);
    }

    @After
    public void destory() throws IOException {
        this.sqlSession.close();
        this.sqlSession.close();
        this.inputStream.close();
    }
}
