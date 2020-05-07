package com.founder.soapui.mapper;

import com.founder.soapui.pojo.WsdlUrl;

import java.util.List;
/**
 * @author ：Mark Wang
 * @date ：Created in 07/05/2020 10:07:06
 * @version: 1.0
 */
public interface WsdlUrlMapper {
    /**
     * Test whether the database connection is successful
     * @return
     */
    public int queryCount();

    /**
     * Get all WSDL locations of FCR interface services
     * @return
     */
    public List<WsdlUrl> queryAllUrl();
}
