package com.xuehui.common;

import com.xuehui.thrift.ServiceProvider;
import com.xuehui.thrift.user.UserInfo;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private ServiceProvider serviceProvider;

    public UserInfo getUserByName(String username) throws TException {
        return serviceProvider.getUserService().getUserByName(username);
    }

    public void regist(UserInfo userInfo) throws TException {
        serviceProvider.getUserService().registe(userInfo);
    }

}
