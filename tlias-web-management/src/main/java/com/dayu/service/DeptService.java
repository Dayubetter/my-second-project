package com.dayu.service;

import com.dayu.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询所有部门信息
     * @return
     */
    List<Dept> findAll();
}
