package com.dayu.service;

import com.dayu.pojo.Emp;
import com.dayu.pojo.EmpQueryParam;
import com.dayu.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public interface EmpService {

    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 保存员工
     * @param emp
     */
    void save(Emp emp);

    /**
     * 分页查询
     * @param page 页码
     * @param pageSize 每页记录数
     * @return
     */
    // PageResult<Emp> page(Integer page, Integer pageSize,String  name, Integer gender, LocalDate begin, LocalDate end);
}
