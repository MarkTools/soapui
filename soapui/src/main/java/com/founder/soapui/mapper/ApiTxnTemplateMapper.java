package com.founder.soapui.mapper;

import com.founder.soapui.pojo.ApiTxnTemplate;

import java.util.List;
/**
 * @author ：Mark Wang
 * @date ：Created in 07/05/2020 10:07:06
 * @version: 1.0
 */
public interface ApiTxnTemplateMapper {
    /**
     * Test whether the database connection is successful
     * @return
     */
    public int queryCount();

    /**
     * Bulk insert data into database table api_txn_template
     * @param apiTxnTemplateList
     */
    public void insertApiTxnTemplate(List<ApiTxnTemplate> apiTxnTemplateList);
}
