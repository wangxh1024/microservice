package com.xuehui.mapper;

import com.xuehui.UserThriftServiceApp;
import com.xuehui.dto.UserDTO;
import com.xuehui.thrift.user.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xuehui.wang
 * @date 2018/12/24 16:27
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserThriftServiceApp.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void regist(){
        UserDTO dto = UserDTO.builder()
                .username("zhangsan")
                .password("123456")
                .email("xxx@163.com")
                .mobile("1333333333")
                .realName("张三")
                .build();
        userMapper.regist(dto);
    }

    @Test
    public void getUserById(){
        UserDTO dto = userMapper.getUserById(2);
        System.out.println(dto);
    }

    @Test
    public void getUserByName(){
        UserDTO dto = userMapper.getUserByName("zhangsan");
        UserInfo info = new UserInfo();
        BeanUtils.copyProperties(dto, info);
        System.out.println(dto);
    }

}
