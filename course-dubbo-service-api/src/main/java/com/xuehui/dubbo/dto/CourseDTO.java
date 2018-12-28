/**
 * 学习使用：
 * 通过dobbo，thrift，spring大家族实现微服务，
 * 通过docker实现微服务容器化
 * 通过mesos，swarm，k8s解决容器编排问题
 */
package com.xuehui.dubbo.dto;

import com.xuehui.dto.UserDTO;
import lombok.Data;

/**
 * @DATE 2018-12-28 14:08
 * @PRO_NAME microservice
 * @AUTHOR wangxh
 * @DESC    【功能说明】
 */
@Data
public class CourseDTO extends UserDTO {

    private String stars;

    private String desc;

}
