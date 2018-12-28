package com.xuehui.service;

import com.xuehui.thrift.ServiceProvider;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MsgService {

    @Autowired
    private ServiceProvider serviceProvider;

    @Async
    public void sendEmailMsg(String email, String msg) throws TException {
        serviceProvider.getMessageService().sendEmailMessage(email, msg);
    }

    @Async
    public void sendPhoneMsg(String mobile, String msg) throws TException{
        serviceProvider.getMessageService().sendMobileMessage(mobile, msg);
    }

}