package com.dayu.service;

import com.dayu.pojo.Emp;
import com.dayu.pojo.EmpQueryParam;
import com.dayu.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

    /**
     * 保存员工
     * @param emp
     */
    void save(Emp emp);

    /**
     * 批量删除员工
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 分页查询
     * @param page 页码
     * @param pageSize 每页记录数
     * @return
     */
    // PageResult<Emp> page(Integer page, Integer pageSize,String  name, Integer gender, LocalDate begin, LocalDate end);
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 根据Id查询员工信息
     * @param id
     * @return
     */
    Emp getInfo(Integer id);

    /**
     * 更新员工信息
     * @param emp
     */
    void update(Emp emp);
}
