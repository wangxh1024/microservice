package com.xuehui.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xuehui.wang
 * @date 2018/12/22 20:06
 * @desc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> implements Serializable {

    public static final Response LOGIN_SUCCESS = new Response("200", "登陆成功", null);

    private String code;

    private String msg;

    private T data;

    public static <T> Response ok(T data){
        Response response = new Response("200", "处理成功", data);
        return response;
    }

    public static <T> Response serviceFail(String msg){
        Response response = new Response();
        response.setMsg(msg);
        response.setCode("500");
        return response;
    }

}
