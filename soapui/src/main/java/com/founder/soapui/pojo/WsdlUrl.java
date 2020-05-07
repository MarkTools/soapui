package com.founder.soapui.pojo;
/**
 * @author ：Mark Wang
 * @date ：Created in 07/05/2020 10:07:06
 * @description： Encapsulates the necessary information according to development requirements
 * @modified By：
 * @version: 1.0
 */
public class WsdlUrl {
    private String serviceId;
    private String url;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
