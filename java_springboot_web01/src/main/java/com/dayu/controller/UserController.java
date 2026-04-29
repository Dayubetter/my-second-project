package com.dayu.controller;

import com.dayu.pojo.User;
import com.dayu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户信息控制类
 */
@RestController // 封装了一个@ResponseBody 作用：将controller返回值直接作为响应体数据返回给浏览器 返回值是对象或者集合的时候直接转成json响应给浏览器
public class UserController {

    @Autowired // 程序运行时,会自动的查询该类型的bean对象，并赋值给该成员变量
    private UserService userService;

    //    @RequestMapping("/list")
//    public List<User> list() {
//        // 1. 加载并读取User.txt文件，获取用户数据
//        InputStream in = this.getClass().getClassLoader().getResourceAsStream("user.txt");
//        ArrayList<String> lines = IoUtil.readLines(in, StandardCharsets.UTF_8, new ArrayList<>());
//        // 2。解析用户信息，封装为User对象 -》list集合
//        List<User> userList = lines.stream().map(line -> {
//            String[] split = line.split(",");
//            Integer id = Integer.parseInt(split[0]);
//            String username = split[1];
//            String password = split[2];
//            String name = split[3];
//            Integer age = Integer.parseInt(split[4]);
//            LocalDateTime updateTime = LocalDateTime.parse(split[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            return new User(id, username, password, name, age, updateTime);
//        }).collect(Collectors.toList());
//
//        // 3. 返回数据(json)
//        return userList;
//    }
    @RequestMapping("/list")
    public List<User> list() {
        // 1. 调用service，获取用户数据
        List<User> userList = userService.findAll();


        // 3. 返回数据(json)
        return userList;
    }
}
