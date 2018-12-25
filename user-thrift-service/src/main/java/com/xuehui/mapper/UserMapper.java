package com.xuehui.mapper;

import com.xuehui.model.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author xuehui.wang
 * @date 2018/12/22 11:46
 * @desc
 */
@Mapper
public interface UserMapper {

    @Select("select id, username, password, real_name as realName, email, mobile from tb_user where id = #{id};")
    UserInfo getUserById(@Param("id") int id);

    @Select("select id, username, password, real_name as realName, email, mobile from tb_user where username = #{username};")
    UserInfo getUserByName(@Param("username") String username);

    @Insert("insert into tb_user (username, password, real_name, email, mobile) values(#{u.username}, #{u.password}, #{u.realName}, #{u.email}, #{u.mobile})")
    void regist(@Param("u") UserInfo userInfo);

}
