package com.founder.soapui.util;

import com.founder.soapui.mapper.WsdlUrlMapper;
import com.founder.soapui.pojo.WsdlUrl;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
/**
 * @author ：Mark Wang
 * @date ：Created in 07/05/2020 10:07:06
 * @description： Tools of WSDL
 * @modified By：
 * @version: 1.0
 */
public class WsdlUrlUtil {

    private static SqlSession sqlSession=null;

    /**
     * Get all WSDL locations of FCR interface services
     * @return
     */
    public static List<WsdlUrl> queryAllWsdlUrl () {
        sqlSession = MybatisUtil.createSqlSession();
        List<WsdlUrl> list = sqlSession.getMapper(WsdlUrlMapper.class).queryAllUrl();
        return list;
    }
}
