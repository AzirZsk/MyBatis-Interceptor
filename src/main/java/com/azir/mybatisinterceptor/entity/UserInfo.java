package com.azir.mybatisinterceptor.entity;

import lombok.Data;

/**
 * @author zhangshukun
 * @since 2024/08/02
 */
@Data
public class UserInfo {

    private Integer id;

    private String name;

    private Integer age;

    private Integer tenantId;
}
