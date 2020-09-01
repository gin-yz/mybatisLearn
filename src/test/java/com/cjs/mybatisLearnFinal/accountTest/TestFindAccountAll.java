package com.cjs.mybatisLearnFinal.accountTest;

import com.cjs.mybatisLearnFinal.dao.AccountDao;
import com.cjs.mybatisLearnFinal.domain.Account;
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

public class TestFindAccountAll {
    private SqlSession sqlSession;
    private InputStream inputStream;

    @Before
    public void init() throws IOException {
        this.inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(this.inputStream);

        this.sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    public void findAccountAll() {
        AccountDao accountDao = this.sqlSession.getMapper(AccountDao.class);

        List<Account> accountList = accountDao.findAccountAll();

        accountList.forEach(System.out::println);
    }

    @After
    public void destory() throws IOException {
        this.sqlSession.commit();
        this.sqlSession.close();
        this.inputStream.close();
    }
}
