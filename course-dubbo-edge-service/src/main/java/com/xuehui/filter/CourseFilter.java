/**
 * 学习使用：
 * 通过dobbo，thrift，spring大家族实现微服务，
 * 通过docker实现微服务容器化
 * 通过mesos，swarm，k8s解决容器编排问题
 */
package com.xuehui.filter;

import com.xuehui.dto.UserDTO;
import com.xuehui.user.filter.LoginFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @DATE 2018-12-28 16:07
 * @PRO_NAME microservice
 * @AUTHOR wangxh
 * @DESC    【功能说明】
 */
@Component
public class CourseFilter extends LoginFilter {

    @Value("${user.edge.service.url}")
    private String userEdgeServiceUrl;

    @Override
    protected void login(HttpServletRequest request, HttpServletResponse response, UserDTO userDto) {
        request.setAttribute("user", userDto);
    }

    @Override
    protected String userEdgeServiceAddr() {
        return userEdgeServiceUrl;
    }

}
