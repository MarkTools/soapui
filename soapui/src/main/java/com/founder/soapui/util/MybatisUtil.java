package com.founder.soapui.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
/**
 * @author ：Mark Wang
 * @date ：Created in 07/05/2020 10:07:06
 * @description： Tools of Mybatis
 * @modified By：
 * @version: 1.0
 */
public class MybatisUtil {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        String source = "com.founder.soapui/mybatis-config.xml";
        /*get inputStream*/
        try {
            InputStream inputStream = Resources.getResourceAsStream(source);
            /*创建一个工厂 sqlSessionFactory*/
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Open a SQL session
     * @return
     */
    public static SqlSession createSqlSession() {
        return sqlSessionFactory.openSession();
    }

    /**
     * Close connection release resource
     * @param sqlSession
     */
    public static void close(SqlSession sqlSession) {
        if (sqlSession != null) {
            sqlSession.close();
        }
    }
}
