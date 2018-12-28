package com.xuehui.thrift;

import com.xuehui.thrift.user.UserService;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author xuehui.wang
 * @date 2018/12/22 19:56
 * @desc
 */
@Component
public class ServiceProvider {

    enum ServiceType{
        USER,
        MSG
    }

    @Value("${thrift.user.ip}")
    private String thriftUserIp;

    @Value("${thrift.user.port}")
    private int thriftUserPort;

    public UserService.Client getUserService(){
        return getService(thriftUserIp, thriftUserPort, ServiceType.USER);
    }

    private <T> T getService(String serviceIp, int servicePort, ServiceType serviceType){
        TSocket socket= new TSocket(serviceIp, servicePort);
        TTransport transport = new TFramedTransport(socket);
        try {
            transport.open();
        } catch (TTransportException e) {
            return null;
        }
        TBinaryProtocol protocol = new TBinaryProtocol(transport);
        TServiceClient client = null;
        switch (serviceType){
            case USER:
                client = new UserService.Client(protocol);
                break;
        }
        return (T)client;
    }

}
