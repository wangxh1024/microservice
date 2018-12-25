package com.xuehui.mapper;

import com.xuehui.UserThriftServiceApp;
import com.xuehui.model.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
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
        UserInfo info = UserInfo.builder()
                .username("zhangsan")
                .password("123456")
                .email("xxx@163.com")
                .mobile("1333333333")
                .realName("张三")
                .build();
        userMapper.regist(info);
    }

    @Test
    public void getUserById(){
        UserInfo user = userMapper.getUserById(1);
        System.out.println(user);
    }

    @Test
    public void getUserByName(){
        UserInfo user = userMapper.getUserByName("zhangsan");
        System.out.println(user);
    }

}
