package com.dayu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 实体类用来封装数据库查询返回的信息，需要和数据库中的表结构一致
 */
@Data
@NoArgsConstructor
public class User {
    private Integer id; // int有默认值0，用包装类型 Integer
    private String username;
    private String password;
    private String name;
    private Integer age;

    public User(Integer id, String username, String password, String name, Integer age) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
    }
}
