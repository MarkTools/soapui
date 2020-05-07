package com.founder.soapui;

import com.eviware.soapui.impl.wsdl.WsdlOperation;
/**
 * @author ：Mark Wang
 * @date ：Created in 07/05/2020 10:07:06
 * @description： Encapsulates the necessary information of WsdlOperation
 * @modified By：
 * @version: 1.0
 */
public class OperationInfo {
    private String operationName;
    private String requestXml;
    private String responseXml;

    public OperationInfo(WsdlOperation operation) {
        operationName = operation.getName();
        requestXml = operation.createRequest( true );
        responseXml = operation.createResponse(true);
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getRequestXml() {
        return requestXml;
    }

    public void setRequestXml(String requestXml) {
        this.requestXml = requestXml;
    }

    public String getResponseXml() {
        return responseXml;
    }

    public void setResponseXml(String responseXml) {
        this.responseXml = responseXml;
    }
}