package com.kaishengit.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * Created by Administrator on 2017/10/23.
 * @author ztl
 */
public class GetSqlSessionFactory {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        if (sqlSessionFactory == null) {
            try {
                InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (Exception e) {
                throw new RuntimeException(e.getCause());
            }
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
    /**
     * @return SqlSession对象
     */
    public static SqlSession openSession(){
        return getSqlSessionFactory().openSession();
    }
}
