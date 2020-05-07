package com.founder.soapui;

import com.founder.soapui.pojo.ApiTxnTemplate;
import com.founder.soapui.pojo.WsdlUrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * @author ：Mark Wang
 * @date ：Created in 07/05/2020 10:07:06
 * @description： Batch parsing WSDL to generate request message
 * @modified By：
 * @version: 1.0
 */
public class BatchWsdlParse {

    private static List<ApiTxnTemplate> soapContext = new ArrayList<>();
    private static List<String> soapRequest = null;

    /**
     * Parsing WSDL to get request message
     * @param wsdlLocation
     * @return
     * @throws Exception
     */
    public static List<ApiTxnTemplate> wsdlParse (String wsdlLocation) throws Exception {
        WsdlInfo wsdlInfo = new WsdlInfo(wsdlLocation);
        for (InterfaceInfo interfaceInfo : wsdlInfo.getInterfaces()) {
            String requestXml = interfaceInfo.getOperations().get(0).getRequestXml();
            soapRequest.add(requestXml);
        }
        return soapContext;
    }

    /**
     * 批量解析WSDL
     * @param wsdlMap
     * @return
     * @throws Exception
     */
    public static List<ApiTxnTemplate> batchWsdlParse (Map<String, String> wsdlMap) throws Exception {
        for (Map.Entry<String, String> entry : wsdlMap.entrySet()) {
            WsdlInfo wsdlInfo = new WsdlInfo(entry.getValue());
            for (InterfaceInfo interfaceInfo : wsdlInfo.getInterfaces()) {
                ApiTxnTemplate att = parseInterfaceInfo(interfaceInfo, entry.getKey());
                soapContext.add(att);
            }
        }
        return soapContext;
    }

    /**
     * 批量解析WSDL
     * @param wsdlList
     * @return
     * @throws Exception
     */
    public static List<ApiTxnTemplate> batchWsdlParse (List<WsdlUrl> wsdlList) throws Exception {
        for (WsdlUrl wsdlUrl : wsdlList) {
            WsdlInfo wsdlInfo = new WsdlInfo(wsdlUrl.getUrl());
            for (InterfaceInfo interfaceInfo : wsdlInfo.getInterfaces()) {
                ApiTxnTemplate att = parseInterfaceInfo(interfaceInfo, wsdlUrl.getServiceId());
                soapContext.add(att);
            }
        }
        return soapContext;
    }

    /**
     * 解析InterfaceInfo
     * @param interfaceInfo
     * @param serviceId
     * @return
     */
    public static ApiTxnTemplate parseInterfaceInfo (InterfaceInfo interfaceInfo, String serviceId) {
        ApiTxnTemplate apiTxnTemplate = new ApiTxnTemplate();

        String requestUrl = interfaceInfo.getAddress()[0];
        String requestTemplate = interfaceInfo.getOperations().get(0).getRequestXml();
        String interfaceName = interfaceInfo.getInterfaceName();
        String binding = "";
        if (interfaceName.contains("Soap11Binding")) {
            binding = "soap 1.1";
        }else if (interfaceName.contains("Soap12Binding")) {
            binding = "soap 1.2";
        }

        apiTxnTemplate.setChannelId("FCR");
        apiTxnTemplate.setTranType("REQ");
        apiTxnTemplate.setBinding(binding);

        apiTxnTemplate.setServiceId(serviceId);
        apiTxnTemplate.setRequestUrl(requestUrl);
        apiTxnTemplate.setRequestTemplate(requestTemplate);

        return apiTxnTemplate;
    }

}
