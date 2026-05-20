package com.dayu.service;

import com.dayu.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询所有部门信息
     * @return
     */
    List<Dept> findAll();

    /**
     * 根据ID查询部门信息
     * @param id
     * @return
     */
    void deleteById(Integer id);

    /**
     * 添加部门信息
     * @param dept
     */
    void add(Dept dept);

    /**
     * 根据ID查询部门信息
     * @param id
     * @return
     */
    Dept getById(Integer id);

    /**
     * 修改部门信息
     * @param dept
     */
    void update(Dept dept);
}
