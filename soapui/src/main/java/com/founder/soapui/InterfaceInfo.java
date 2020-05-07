package com.founder.soapui;

import com.eviware.soapui.impl.wsdl.WsdlInterface;
import com.eviware.soapui.impl.wsdl.WsdlOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：Mark Wang
 * @date ：Created in 07/05/2020 10:07:06
 * @description： Encapsulates the necessary information of WsdlInterface
 * @modified By：
 * @version: 1.0
 */
public class InterfaceInfo {
    private String interfaceName;
    private List<OperationInfo> operations;
    private String[] address;

    public InterfaceInfo(WsdlInterface wsdlInterface) {
        this.interfaceName = wsdlInterface.getName();
        this.address = wsdlInterface.getEndpoints();
        int operationNum = wsdlInterface.getOperationCount();
        List<OperationInfo> operations = new ArrayList<OperationInfo>();

        for(int i = 0; i < operationNum; i++) {
            WsdlOperation operation = ( WsdlOperation )wsdlInterface.getOperationAt( i );
            OperationInfo operationInfo = new OperationInfo(operation);
            operations.add(operationInfo);
        }

        this.operations = operations;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public List<OperationInfo> getOperations() {
        return operations;
    }

    public void setOperations(List<OperationInfo> operations) {
        this.operations = operations;
    }

    public String[] getAddress() {
        return address;
    }

    public void setAddress(String[] address) {
        this.address = address;
    }
}