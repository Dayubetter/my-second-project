package com.dayu.demo2reflection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    private String name;
    private int age;
    private String sex;
    private String course;
    private String school;
    private String address;
    private String phone;
    private Dog dog;
}
