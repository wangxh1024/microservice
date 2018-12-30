package com.xuehui.thrift;

import com.xuehui.thrift.message.MessageService;
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

    @Value("${thrift.message.ip}")
    private String thriftMessageIp;

    @Value("${thrift.message.port}")
    private int thriftMessagePort;

    public UserService.Client getUserService(){
        System.out.println("thriftUserIp:" + thriftUserIp + "    thriftUserPort:" + thriftUserPort);
        return getService(thriftUserIp, thriftUserPort, ServiceType.USER);
    }

    public MessageService.Client getMessageService(){
        return getService(thriftMessageIp, thriftMessagePort, ServiceType.MSG);
    }

    private <T> T getService(String serviceIp, int servicePort, ServiceType serviceType){
        TSocket socket= new TSocket(serviceIp, servicePort);
        TTransport transport = new TFramedTransport(socket);
        try {
            transport.open();
            System.out.println("启动成功");
        } catch (TTransportException e) {
            System.out.println("启动失败");
            return null;
        }
        TBinaryProtocol protocol = new TBinaryProtocol(transport);
        TServiceClient client = null;
        switch (serviceType){
            case MSG:
                client = new MessageService.Client(protocol);
                break;
            case USER:
                client = new UserService.Client(protocol);
                break;
        }
        return (T)client;
    }

}
