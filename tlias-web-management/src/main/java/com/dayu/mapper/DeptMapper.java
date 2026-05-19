package com.dayu.mapper;

import com.dayu.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    /**
     * 查询所有部门数据
     * @return
     */
    // 方式一：手动结果映射
//    @Results({
//            @Result(column = "create_time", property = "createTime"),
//            @Result(column = "update_time", property = "updateTime")
//    })
    // 方式二：起别名
    @Select("select id, name, create_time createTime, update_time updateTime from dept order by update_time desc")
    List<Dept> findAll();

    /**
     * 根据ID删除部门
     * @param id
     */
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);
}
