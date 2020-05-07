package com.founder.soapui;

import com.founder.soapui.mapper.ApiTxnTemplateMapper;
import com.founder.soapui.pojo.ApiTxnTemplate;
import com.founder.soapui.pojo.WsdlUrl;
import com.founder.soapui.util.MybatisUtil;
import com.founder.soapui.util.WsdlUrlUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class WSDLParseTest {
    private SqlSession sqlSession=null;

    @Before
    public void before () {
        sqlSession = MybatisUtil.createSqlSession();
    }

    @After
    public void after () {
        MybatisUtil.close(sqlSession);
    }

    @Test
    public void insertApiTxnTemplateTest() throws Exception {
        List<WsdlUrl> list = WsdlUrlUtil.queryAllWsdlUrl();
        List<ApiTxnTemplate> apiTxnTemplatesList = new BatchWsdlParse().batchWsdlParse(list);
        sqlSession.getMapper(ApiTxnTemplateMapper.class).insertApiTxnTemplate(apiTxnTemplatesList);
        sqlSession.commit();
    }
}
