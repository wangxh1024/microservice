/**
 * 学习使用：
 * 通过dobbo，thrift，spring大家族实现微服务，
 * 通过docker实现微服务容器化
 * 通过mesos，swarm，k8s解决容器编排问题
 */
package com.xuehui.dubbo.service;

import com.xuehui.dubbo.dto.CourseDTO;

import java.util.List;

/**
 * @DATE 2018-12-28 14:07
 * @PRO_NAME microservice
 * @AUTHOR wangxh
 * @DESC    【功能说明】
 */
public interface ICourseService {

    List<CourseDTO> couseList();

}
