/**
 * 学习使用：
 * 通过dobbo，thrift，spring大家族实现微服务，
 * 通过docker实现微服务容器化
 * 通过mesos，swarm，k8s解决容器编排问题
 */
package com.xuehui.dto;

import lombok.*;

import java.io.Serializable;

/**
 * @DATE 2018-12-27 22:15
 * @PRO_NAME microservice
 * @AUTHOR wangxh
 * @DESC    【功能说明】
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    private Long id;

    private String username;

    private String password;

    private String realName;

    private String mobile;

    private String email;

}
