package com.founder.soapui;

import com.eviware.soapui.impl.wsdl.WsdlInterface;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.support.wsdl.WsdlImporter;

import java.util.ArrayList;
import java.util.List;
/**
 * @author ：Mark Wang
 * @date ：Created in 07/05/2020 10:07:06
 * @description： Load information according to wsdl location
 * @modified By：
 * @version: 1.0
 */
public class WsdlInfo {
    private String wsdlName;
    private List<InterfaceInfo> interfaces;

    public WsdlInfo(String path) throws Exception {
        WsdlProject project = new WsdlProject();
        WsdlInterface[] wsdlInterfaces = WsdlImporter.importWsdl( project, path );
        this.wsdlName = path;
        if(null != wsdlInterfaces)
        {
            List<InterfaceInfo> interfaces = new ArrayList<InterfaceInfo>();
            for(WsdlInterface wsdlInterface : wsdlInterfaces)
            {
                InterfaceInfo interfaceInfo = new InterfaceInfo(wsdlInterface);
                interfaces.add(interfaceInfo);
            }
            this.interfaces = interfaces;
        }
    }

    public String getWsdlName() {
        return wsdlName;
    }

    public void setWsdlName(String wsdlName) {
        this.wsdlName = wsdlName;
    }

    public List<InterfaceInfo> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<InterfaceInfo> interfaces) {
        this.interfaces = interfaces;
    }
}