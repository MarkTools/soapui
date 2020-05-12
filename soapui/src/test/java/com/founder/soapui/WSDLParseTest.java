package com.founder.soapui;

import com.founder.soapui.mapper.ApiTxnTemplateMapper;
import com.founder.soapui.pojo.ApiTxnTemplate;
import com.founder.soapui.pojo.WsdlUrl;
import com.founder.soapui.util.MybatisUtil;
import com.founder.soapui.util.WsdlUrlUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author ：Mark Wang
 * @date ：Created in 07/05/2020 10:07:06
 * @description： Test to parse wsdl
 * @modified By：
 * @version: 1.0
 */
public class WSDLParseTest {
    private final static Logger log = Logger.getLogger(WSDLParseTest.class);
    private SqlSession sqlSession=null;
    private String wsdlLocation = "http://192.168.1.18:8001/CHNAppxServices/services/XfaceFCYBuyingandSellingTransactionWrapper?wsdl";

    @Before
    public void before () {
        sqlSession = MybatisUtil.createSqlSession();
    }

    @After
    public void after () {
        MybatisUtil.close(sqlSession);
    }

    @Test
    public void oracleJdbcTest () {
        int count = sqlSession.getMapper(ApiTxnTemplateMapper.class).queryCount();
        System.out.println(count);
    }
    /**
     * According a wsdl location to generate request messages
     * @throws Exception
     */
    @Test
    public void wsdlParseTest () throws Exception {
        List<String> requestList = BatchWsdlParse.wsdlParse(wsdlLocation);
        for (String request : requestList) {
            System.out.println(request);
        }
    }

    @Test
    public void wsdlParseAndUpdateTest () throws Exception {
        List<String> requestList = BatchWsdlParse.wsdlParseAndUpdate(wsdlLocation);
        for (String request : requestList) {
            System.out.println(request);
        }
    }

    /**
     * According some wsdl locations to generate request messages
     * @throws Exception
     */
    @Test
    public void insertApiTxnTemplateTest() throws Exception {
        List<WsdlUrl> list = WsdlUrlUtil.queryAllWsdlUrl();
        List<ApiTxnTemplate> apiTxnTemplatesList = new BatchWsdlParse().batchWsdlParse(list);

        sqlSession.getMapper(ApiTxnTemplateMapper.class).batchInsertApiTxnTemplate(apiTxnTemplatesList);
        sqlSession.commit();
    }

}
