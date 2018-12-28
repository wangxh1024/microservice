/**
 * 学习使用：
 * 通过dobbo，thrift，spring大家族实现微服务，
 * 通过docker实现微服务容器化
 * 通过mesos，swarm，k8s解决容器编排问题
 */
package com.xuehui.helper;

import com.xuehui.UserThriftServiceApp;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @DATE 2018-12-26 15:16
 * @PRO_NAME microservice
 * @AUTHOR wangxh
 * @DESC    【redis工具类的测试类】
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserThriftServiceApp.class)
public class RedisHelperTest {

    @Autowired
    private RedisHelper redisHelper;

    private String key = "key";

    private String value = "value";

    private long timeout = 10l;

    @Test
    public void set(){
        redisHelper.set(key, value);
    }

    @Test
    public void get(){
        String value = (String)redisHelper.get(key);
        System.out.println(value);
        Assert.assertEquals("value", value);
    }

    @Test
    public void setWithTime(){
        redisHelper.set(key, value, timeout);
    }
}
