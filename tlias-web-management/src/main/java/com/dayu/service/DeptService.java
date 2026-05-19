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
}
