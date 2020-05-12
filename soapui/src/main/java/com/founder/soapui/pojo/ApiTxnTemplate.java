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
    private String requestTemplate1;
    private String requestTemplate2;

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

    public String getRequestTemplate1() {
        return requestTemplate1;
    }

    public void setRequestTemplate1(String requestTemplate1) {
        this.requestTemplate1 = requestTemplate1;
    }

    public String getRequestTemplate2() {
        return requestTemplate2;
    }

    public void setRequestTemplate2(String requestTemplate2) {
        this.requestTemplate2 = requestTemplate2;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }
}
