package com.xuehui.service;

import com.xuehui.dto.UserDTO;
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
        UserDTO dto = userMapper.getUserById(id);
        UserInfo info = new UserInfo();
        BeanUtils.copyProperties(dto, info);
        return info;
    }

    public UserInfo getUserByName(String username) throws TException {
        UserDTO dto = userMapper.getUserByName(username);
        UserInfo info = new UserInfo();
        BeanUtils.copyProperties(dto, info);
        return info;
    }

    public void registe(UserInfo userInfo) throws TException {
        UserDTO dto = UserDTO.builder().build();
        BeanUtils.copyProperties(userInfo, dto);
        userMapper.regist(dto);
    }
}
