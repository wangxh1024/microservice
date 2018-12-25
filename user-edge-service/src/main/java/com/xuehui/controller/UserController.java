package com.xuehui.controller;

import com.xuehui.common.Response;
import com.xuehui.thrift.ServiceProvider;
import com.xuehui.thrift.user.UserInfo;
import com.xuehui.vo.UserVO;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * @author xuehui.wang
 * @date 2018/12/22 19:54
 * @desc
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ServiceProvider serviceProvider;

    @GetMapping("/login")
    @ResponseBody
    public Response login(String username, String password){
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return Response.serviceFail("用户名或密码为空");
        }
        UserVO vo = null;
        try {
            UserInfo user = serviceProvider.getUserService().getUserByName(username);
            if(Objects.isNull(user)){
                return Response.serviceFail("该用户不存在");
            }
            //参数校验
            if(!StringUtils.equals(user.getPassword(), password)){
                return Response.serviceFail("用户名或者密码错误");
            }
            // 发送邮箱
            serviceProvider.getMessageService().sendEmailMessage(user.getEmail(), "用户登陆成功");
        } catch (TException e) {
            e.printStackTrace();
            return Response.serviceFail("请求处理失败");
        }
        return Response.LOGIN_SUCCESS;
    }
}
