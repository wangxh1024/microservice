/**
 * 学习使用：
 * 通过dobbo，thrift，spring大家族实现微服务，
 * 通过docker实现微服务容器化
 * 通过mesos，swarm，k8s解决容器编排问题
 */
package com.xuehui.helper;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @DATE 2018-12-26 15:07
 * @PRO_NAME microservice
 * @AUTHOR wangxh
 * @DESC    【提供redis基本操作】
 */
@Component
public class RedisHelper {

    @Autowired
    private RedisTemplate redisTemplate;

    public <T> void set(String key, T value){
        if(Objects.isNull(value) || Strings.isNullOrEmpty(key)){
            throw new IllegalArgumentException("参数不合法");
        }
        redisTemplate.opsForValue().set(key, value);
    }

    public <T> void set(String key, T value, Long timeout){
        if(Objects.isNull(value) || Strings.isNullOrEmpty(key)){
            throw new IllegalArgumentException("参数不合法");
        }
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }


    public <T> T get(String key){
        if(Strings.isNullOrEmpty(key)){
            throw new IllegalArgumentException("参数不合法");
        }
        return (T)redisTemplate.opsForValue().get(key);
    }

    public void expire(String key, Long timeout){
        if(Strings.isNullOrEmpty(key)){
            throw new IllegalArgumentException("参数不合法");
        }
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }
}
