package com.founder.soapui.pojo;
/**
 * @author ：Mark Wang
 * @date ：Created in 07/05/2020 10:07:06
 * @description： Encapsulates the necessary information according to development requirements
 * @modified By：
 * @version: 1.0
 */
public class ApiTxnTemplate {
    private String channelId;
    private String serviceId;
    private String tranType;
    private String binding;
    private String requestUrl;
    private String requestTemplate;

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public String getRequestTemplate() {
        return requestTemplate;
    }

    public void setRequestTemplate(String requestTemplate) {
        this.requestTemplate = requestTemplate;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }
}
