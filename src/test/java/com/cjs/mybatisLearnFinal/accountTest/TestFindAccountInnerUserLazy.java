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
import java.util.function.Consumer;

public class TestFindAccountInnerUserLazy {
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
    public void findAccountInnerUserLazy() {
        AccountDao accountDao = this.sqlSession.getMapper(AccountDao.class);

        List<Account> accountList = accountDao.findAccountInnerUserLazy();

        //如果遍历了,那么自动注入,执行查找user的任务
//        accountList.forEach(System.out::println);

        //只操作id,和user无关,不查找
        accountList.forEach(new Consumer<Account>() {
            @Override
            public void accept(Account account) {
                System.out.println(account.getId());
            }
        });

    }

    @After
    public void destory() throws IOException {
        this.sqlSession.commit();
        this.sqlSession.close();
        this.inputStream.close();
    }
}
