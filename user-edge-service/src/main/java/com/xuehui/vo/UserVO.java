package com.xuehui.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserVO {

    private int id;

    private String username;

    private String password;

    private String realName;

    private String mobile;

    private String email;

}
