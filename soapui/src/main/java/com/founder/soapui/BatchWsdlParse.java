package com.founder.soapui;

import com.founder.soapui.pojo.ApiTxnTemplate;
import com.founder.soapui.pojo.WsdlUrl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author :Mark Wang
 * @date :Created in 07/05/2020 10:07:06
 * @description: Batch parsing WSDL to generate request message
 * @modified By:
 * @version: 1.0
 */
public class BatchWsdlParse {

    private static List<ApiTxnTemplate> soapContent = new ArrayList<>();
    private static List<String> soapRequest = new ArrayList<>();
    private static String requestXml = "";
    private static String param1Reg = "<xsd:auditComments>.*</xsd:valueDateText>";
    private static String param2RegList = "<xsd1:listOfvalidationErrors>.*</xsd1:validationFlag>";
    private static String param2RegEnrich = "<xsd1:enrichAccountBranch>\\$\\{enrichAccountBranch}</xsd1:enrichAccountBranch>" +
                                            "<xsd1:enrichCustomerId>\\$\\{enrichCustomerId}</xsd1:enrichCustomerId>" +
                                            "<xsd1:enrichMcAccountTitle>\\$\\{enrichMcAccountTitle}</xsd1:enrichMcAccountTitle>" +
                                            "<xsd1:enrichMcaCurrency>\\$\\{enrichMcaCurrency}</xsd1:enrichMcaCurrency>" +
                                            "<xsd1:enrichModuleCode>\\$\\{enrichModuleCode}</xsd1:enrichModuleCode>" +
                                            "<xsd1:enrichSubAccount>\\$\\{enrichSubAccount}</xsd1:enrichSubAccount>" +
                                            "<xsd1:enrichSubAccountProduct>\\$\\{enrichSubAccountProduct}</xsd1:enrichSubAccountProduct>";
    private static String regex = "(?<=\\>)(?:\\s*\t?\r?\n?)(?=\\<)";
    private static String param1  = "<xsd:bankCode>\\${bankCode}</xsd:bankCode>" +
                                    "<xsd:channel>\\${channel}</xsd:channel>" +
                                    "<xsd:externalBranchCode>\\${externalBranchCode}</xsd:externalBranchCode>" +
                                    "<xsd:externalReferenceNo>\\${externalReferenceNo}</xsd:externalReferenceNo>" +
                                    "<xsd:serviceCode>\\${serviceCode}</xsd:serviceCode>" +
                                    "<xsd:transactionBranch>\\${transactionBranch}</xsd:transactionBranch>" +
                                    "<xsd:userId>\\${userId}</xsd:userId>";

    private static String originalReferenceNo  = "<xsd:originalReferenceNo>\\${originalReferenceNo}</xsd:originalReferenceNo>";

    /**
     * Parsing WSDL to get request message and custom content
     * @param wsdlLocation
     * @return
     * @throws Exception
     */
    public static List<String> wsdlParseAndUpdate (String wsdlLocation) throws Exception {
        WsdlInfo wsdlInfo = new WsdlInfo(wsdlLocation);
        for (InterfaceInfo interfaceInfo : wsdlInfo.getInterfaces()) {
            requestXml = interfaceInfo.getOperations().get(0).getRequestXml();
            requestXml = unFormatted(requestXml);
            requestXml = CustomContent(requestXml,"");
            soapRequest.add(requestXml);
        }
        return soapRequest;
    }

    /**
     * Parsing WSDL to get request message
     * @param wsdlLocation
     * @return
     * @throws Exception
     */
    public static List<String> wsdlParse (String wsdlLocation) throws Exception {
        WsdlInfo wsdlInfo = new WsdlInfo(wsdlLocation);
        for (InterfaceInfo interfaceInfo : wsdlInfo.getInterfaces()) {
            requestXml = interfaceInfo.getOperations().get(0).getRequestXml();
            requestXml = unFormatted(requestXml);
            soapRequest.add(requestXml);
        }
        return soapRequest;
    }

    /**
     * Batch wsdl parse
     * @param wsdlMap
     * @return
     * @throws Exception
     */
    public static List<ApiTxnTemplate> batchWsdlParse (Map<String, String> wsdlMap) throws Exception {
        for (Map.Entry<String, String> entry : wsdlMap.entrySet()) {
            WsdlInfo wsdlInfo = new WsdlInfo(entry.getValue());
            for (InterfaceInfo interfaceInfo : wsdlInfo.getInterfaces()) {
                ApiTxnTemplate att = parseInterfaceInfo(interfaceInfo, entry.getKey());
                soapContent.add(att);
            }
        }
        return soapContent;
    }

    /**
     * Batch wsdl parse
     * @param wsdlList
     * @return
     * @throws Exception
     */
    public static List<ApiTxnTemplate> batchWsdlParse (List<WsdlUrl> wsdlList) throws Exception {
        for (WsdlUrl wsdlUrl : wsdlList) {
            WsdlInfo wsdlInfo = new WsdlInfo(wsdlUrl.getUrl());
            for (InterfaceInfo interfaceInfo : wsdlInfo.getInterfaces()) {
                ApiTxnTemplate att = parseInterfaceInfo(interfaceInfo, wsdlUrl.getServiceId());
                soapContent.add(att);
            }
        }
        return soapContent;
    }

    /**
     * parse InterfaceInfo
     * @param interfaceInfo
     * @param serviceId
     * @return
     */
    public static ApiTxnTemplate parseInterfaceInfo (InterfaceInfo interfaceInfo, String serviceId) throws MalformedURLException {
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

        URL url = new URL(requestUrl);
        apiTxnTemplate.setRequestUrl(url.getFile());

        requestTemplate = unFormatted(requestTemplate);
        requestTemplate = CustomContent(requestTemplate, serviceId);

        int length = requestTemplate.length();
        if (length > 4000) {
            apiTxnTemplate.setRequestTemplate1(requestTemplate.substring(0,length/2));
            apiTxnTemplate.setRequestTemplate2(requestTemplate.substring(length/2,length));
        } else {
            apiTxnTemplate.setRequestTemplate1(requestTemplate);
            apiTxnTemplate.setRequestTemplate2("");
        }
        return apiTxnTemplate;
    }

    /**
     * Because the request information is too long, it is partially replaced with custom content
     * @param requestTemplate
     * @param serviceId
     * @return
     */
    public static String CustomContent (String requestTemplate, String serviceId) {
        if (serviceId == "324") {
            requestTemplate = requestTemplate.replaceAll(param1Reg, param1+originalReferenceNo);
        }else {
            requestTemplate = requestTemplate.replaceAll(param1Reg, param1);
        }
        return requestTemplate;
    }

    /**
     * Remove formatting and delete unnecessary for saving space
     * @param requestTemplate
     * @return
     */
    private static String unFormatted(String requestTemplate) {
        if (requestTemplate!=null) {
            requestTemplate = requestTemplate.replaceAll(regex,"");
            requestTemplate = requestTemplate.replaceAll(param2RegList,"");
            requestTemplate = requestTemplate.replaceAll(param2RegEnrich,"");
        }
        return requestTemplate;
    }

}
