/**
 * 学习使用：
 * 通过dobbo，thrift，spring大家族实现微服务，
 * 通过docker实现微服务容器化
 * 通过mesos，swarm，k8s解决容器编排问题
 */
package com.xuehui.common;

import lombok.Builder;
import lombok.Data;

/**
 * @DATE 2018-12-28 10:54
 * @PRO_NAME microservice
 * @AUTHOR wangxh
 * @DESC    【功能说明】
 */
@Data
@Builder
public class LoginResponse extends Response{
    private String token;

    public static Response LOGIN_SUCCESS(String token){
        return LoginResponse.builder().token(token).build();
    }
}
