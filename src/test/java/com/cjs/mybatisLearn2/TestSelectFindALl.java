package com.cjs.mybatisLearn2;

import com.cjs.mybatisLearn2.dao.UserDao;
import com.cjs.mybatisLearn2.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class TestSelectFindALl {
    public static void main(String[] args) throws IOException {
//        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        InputStream inputStream = TestSelectFindALl.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml");

        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);

        List<User> users = userDao.findAll();

        users.stream().map(new Function<User, Date>() {
            @Override
            public Date apply(User user) {
                return user.getBirthday();
            }
        }).forEach(new Consumer<Date>() {
            @Override
            public void accept(Date data) {
                Calendar calendar = Calendar.getInstance();
                calendar.clear();
                calendar.setTime(data);
                System.out.printf("%s-%s-%s-%s:%s:%s%n",calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),calendar.get(Calendar.SECOND));
            }
        });

        sqlSession.close();
        inputStream.close();

    }
}
