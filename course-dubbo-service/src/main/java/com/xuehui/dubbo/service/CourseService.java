/**
 * 学习使用：
 * 通过dobbo，thrift，spring大家族实现微服务，
 * 通过docker实现微服务容器化
 * 通过mesos，swarm，k8s解决容器编排问题
 */
package com.xuehui.dubbo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.collect.Lists;
import com.xuehui.dubbo.dto.CourseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @DATE 2018-12-28 15:07
 * @PRO_NAME microservice
 * @AUTHOR wangxh
 * @DESC    【功能说明】
 */
@Component
@Service(interfaceClass = ICourseService.class)
public class CourseService implements ICourseService{

    @Override
    public List<CourseDTO> couseList() {
        CourseDTO dto = new CourseDTO();
        dto.setDesc("我是老师");
        dto.setStars("1");
        return Lists.newArrayList(dto);
    }

}
