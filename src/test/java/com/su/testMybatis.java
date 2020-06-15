package com.su;

import com.su.dao.UserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

public class testMybatis {

    @Test
    public void run() throws Exception{
        //加载配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFaction对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //创建SqlSession
        SqlSession session = factory.openSession();
        //获取到代理对象
        UserDao dao = session.getMapper(UserDao.class);
        //查询所有
        dao.findAll();
        //关闭资源
        session.close();
        in.close();

    }
}
