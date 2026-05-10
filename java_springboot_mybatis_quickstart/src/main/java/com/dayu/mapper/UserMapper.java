package com.dayu.mapper;

import com.dayu.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper // 应用程序在运行时，会自动为该接口创建一个实现类(代理对象)，并且会自动将该实现类存入IOC容器中 -bean
public interface UserMapper {

    /**
     * 查询所有用户
     * @return
     */
    // @Select("select * from user")
    public List<User> findAll();

    /**
     * 根据id删除用户
     * @param id
     */
    @Delete("delete from user where id = #{id}")
//    public void deleteById(Integer  id);
    public Integer deleteById(Integer  id);

    /**
     * 插入用户
     * @param user
     */
    @Insert("insert into user(id,username,password,name,age) values(#{id},#{username},#{password},#{name},#{age})") // 对象的属性名
    public void insert(User  user);

    /**
     * 修改用户
     * @param user
     */
    @Update("update user set username=#{username},password=#{password},name=#{name},age=#{age} where id=#{id}")
    public void update(User  user);

    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    // 根据官方骨架创建的springboot项目，不用添加@Param
    @Select("select * from user where username=#{username} and password=#{password}")
    public User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password); // 传递多个参数需要使用@Param

}
