/**
 * 学习使用：
 * 通过dobbo，thrift，spring大家族实现微服务，
 * 通过docker实现微服务容器化
 * 通过mesos，swarm，k8s解决容器编排问题
 */
package com.xuehui.course.edge.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xuehui.dto.UserDTO;
import com.xuehui.dubbo.dto.CourseDTO;
import com.xuehui.dubbo.service.ICourseService;
import org.apache.http.HttpRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @DATE 2018-12-28 15:25
 * @PRO_NAME microservice
 * @AUTHOR wangxh
 * @DESC    【功能说明】
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @Reference(check = true)
    private ICourseService courseService;

    @RequestMapping("/list")
    @ResponseBody
    private CourseDTO courseList(ServletRequest httpRequest){
        HttpServletRequest request = (HttpServletRequest)httpRequest;
        UserDTO user = (UserDTO)request.getAttribute("user");
        List<CourseDTO> list = courseService.couseList();
        CourseDTO course = null;
        if(!CollectionUtils.isEmpty(list)){
            course = list.get(0);
            BeanUtils.copyProperties(user, course);
        }
        return course;
    }

}
