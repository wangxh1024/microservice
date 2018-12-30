/**
 * 学习使用：
 * 通过dobbo，thrift，spring大家族实现微服务，
 * 通过docker实现微服务容器化
 * 通过mesos，swarm，k8s解决容器编排问题
 */
package com.xuehui.user.filter;

import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.xuehui.dto.UserDTO;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @DATE 2018-12-27 22:00
 * @PRO_NAME microservice
 * @AUTHOR wangxh
 * @DESC    【功能说明】
 */
public abstract class LoginFilter implements Filter {

    private static Cache<String, UserDTO> cache = CacheBuilder.newBuilder()
            .maximumSize(1024)
            .expireAfterWrite(1800, TimeUnit.SECONDS)
            .build();

    private String userEdgeServiceAddr = "http://localhost:8080";

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        String token = request.getParameter("token");
        if(StringUtils.isBlank(token)){
            Cookie[] cookies = request.getCookies();
            if(Objects.nonNull(cookies)){
                for(Cookie cookie : cookies){
                    if(StringUtils.equals("token", cookie.getName())){
                        token = cookie.getValue();
                    }
                }
            }
        }

        if(StringUtils.isBlank(token)){
            token = request.getHeader("token");
        }

        UserDTO userDto = null;
        if(StringUtils.isNotBlank(token)){
            userDto = cache.getIfPresent(token);
            if(Objects.isNull(userDto)){
                userDto = requestUserDto(token);
                if(Objects.nonNull(userDto)){
                    cache.put(token, userDto);
                }
            }
        }

        if(Objects.isNull(userDto)){
            response.sendRedirect("http://192.168.0.101:8888/user/login");
            return;
        }

        login(request, response, userDto);

        filterChain.doFilter(request, response);
    }

    protected abstract void login(HttpServletRequest request, HttpServletResponse response, UserDTO userDto);

    protected abstract String userEdgeServiceAddr();

    private UserDTO requestUserDto(String token){
        String url = userEdgeServiceAddr() + "/user/authentication";
        UserDTO userDto = null;
        InputStream inputStream = null;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            post.addHeader("token", token);
            HttpResponse response = client.execute(post);
            if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
                throw new RuntimeException("request user info failed! StatusLine:"+response.getStatusLine());
            }
            inputStream = response.getEntity().getContent();
            byte[] bytes = new byte[1024];
            int length = 0;
            StringBuffer sb = new StringBuffer();
            while ((length = inputStream.read(bytes)) > 0){
                sb.append(new String(bytes, 0, length));
            }
            userDto = JSONObject.parseObject(sb.toString(), UserDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(Objects.nonNull(inputStream)){
                try{
                    inputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return userDto;
    }

    public void destroy() {

    }

}
