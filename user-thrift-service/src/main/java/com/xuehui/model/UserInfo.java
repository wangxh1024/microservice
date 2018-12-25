package com.xuehui.model;

import lombok.*;

/**
 * @author xuehui.wang
 * @date 2018/12/24 15:25
 * @desc 数据库实体类-用户信息
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    private Long id;

    private String username;

    private String password;

    private String realName;

    private String email;

    private String mobile;

}
