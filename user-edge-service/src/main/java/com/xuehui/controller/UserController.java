package com.xuehui.controller;

import com.xuehui.common.LoginResponse;
import com.xuehui.common.Response;
import com.xuehui.service.UserService;
import com.xuehui.dto.UserDTO;
import com.xuehui.helper.RedisHelper;
import com.xuehui.service.MsgService;
import com.xuehui.thrift.user.UserInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Random;

/**
 * @author xuehui.wang
 * @date 2018/12/22 19:54
 * @desc
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MsgService msgService;

    @Autowired
    private RedisHelper redisHelper;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Response login(String username, String password){
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return Response.serviceFail("用户名或密码为空");
        }
        try {
            System.out.println("调用开始");
            UserInfo user = userService.getUserByName(username);
            if(Objects.isNull(user)){
                return Response.serviceFail("该用户不存在");
            }
            //参数校验
            if(!StringUtils.equals(user.getPassword(), password)){
                return Response.serviceFail("用户名或者密码错误");
            }
            //生成token
            String token = randomCode("0123456789abcdefghijklmnopqrstuvwxyz", 32);
            UserDTO dto = UserDTO.builder().build();
            BeanUtils.copyProperties(user, dto);
            redisHelper.set(token, dto, 1800L);
            return LoginResponse.LOGIN_SUCCESS(token);
        } catch (TException e) {
            e.printStackTrace();
            return Response.serviceFail("请求处理失败");
        }
    }

    @PostMapping("/verifyCode")
    @ResponseBody
    public Response verifyCode(
            @RequestParam(value = "mobile", required = false) String mobile,
            @RequestParam(value = "email", required = false) String email){
        if(StringUtils.isBlank(mobile) && StringUtils.isBlank(email)){
            return Response.serviceFail("手机号和邮箱不能同时为空");
        }
        try{
            //生成验证码
            String code = randomCode("1234567890", 6);
            if(StringUtils.isNotBlank(mobile)){
                msgService.sendPhoneMsg(mobile, code);
                redisHelper.set(mobile, code, 6000L);
            }
            if(StringUtils.isNotBlank(email)){
                msgService.sendEmailMsg(email, code);
                redisHelper.set(email, code, 6000L);
            }
        }catch (Exception e){
            return Response.exception(e);
        }
        return Response.ok();
    }

    @PostMapping("/regist")
    @ResponseBody
    public Response register(
                            @RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam(value = "mobile", required = false) String mobile,
                            @RequestParam(value = "email", required = false) String email,
                            @RequestParam("realName") String realName,
                            @RequestParam("verifyCode") String verifyCode){
        try {
            if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
                return Response.serviceFail("用户名或者密码不能为空");
            }
            if(StringUtils.isNotBlank(mobile)){
                String code = redisHelper.get(mobile);
                if(!StringUtils.equals(code, verifyCode)){
                    return Response.serviceFail("验证码错误");
                }
            }
            if(StringUtils.isNotBlank(email)){
                String code = redisHelper.get(email);
                if(!StringUtils.equals(code, verifyCode)){
                    return Response.serviceFail("验证码错误");
                }
            }
            UserInfo info = new UserInfo();
            info.setUsername(username);
            info.setPassword(password);
            info.setRealName(realName);
            info.setMobile(mobile);
            info.setEmail(email);
            userService.regist(info);
        } catch (TException e) {
            e.printStackTrace();
            return Response.serviceFail("注册失败");
        }
        return Response.ok();
    }

    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    @ResponseBody
    public UserDTO authentication(@RequestHeader("token") String token){
        return redisHelper.get(token);
    }


    public String randomCode(String src, int count){
        if(StringUtils.isBlank(src)){
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for(int i  = 0; i < count; i++){
            char c = src.charAt(random.nextInt(src.length()));
            buffer.append(c);
        }
        return buffer.toString();
    }
}
