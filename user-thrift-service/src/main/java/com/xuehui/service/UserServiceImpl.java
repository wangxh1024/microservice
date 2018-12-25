package com.xuehui.service;

import com.xuehui.mapper.UserMapper;
import com.xuehui.thrift.user.UserInfo;
import com.xuehui.thrift.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xuehui.wang
 * @date 2018/12/22 11:12
 * @desc
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService.Iface {

    @Autowired
    private UserMapper userMapper;

    public UserInfo getUserById(int id) throws TException {
        com.xuehui.model.UserInfo user = userMapper.getUserById(id);
        UserInfo info = new UserInfo();
        BeanUtils.copyProperties(user, info);
        return info;
    }

    public UserInfo getUserByName(String username) throws TException {
        com.xuehui.model.UserInfo user = userMapper.getUserByName(username);
        UserInfo info = new UserInfo();
        BeanUtils.copyProperties(user, info);
        return info;
    }

    public void registe(UserInfo userInfo) throws TException {
        com.xuehui.model.UserInfo info = new com.xuehui.model.UserInfo();
        BeanUtils.copyProperties(userInfo, info);
        userMapper.regist(info);
    }
}
