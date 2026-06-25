package com.dayu.controller;

import com.dayu.pojo.Emp;
import com.dayu.pojo.EmpQueryParam;
import com.dayu.pojo.PageResult;
import com.dayu.pojo.Result;
import com.dayu.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * 员工管理的controller
 */
@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;
    /**
     * 分页查询
     * @return
     */
    /*@GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String  name, Integer gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("分页查询：{}，{},{},{},{},{}", page, pageSize, name, gender, begin, end);
        PageResult<Emp> pageResult = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageResult);
    }*/


    @GetMapping
    public Result page(EmpQueryParam  empQueryParam){
        log.info("分页查询：{}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 新增员工 将json格式的数据封装到一个对象中
     * @return
     */
    @PostMapping
    public Result save(@RequestBody Emp  emp){
        log.info("保存员工：{}", emp);
        empService.save(emp);
        return Result.success();
    }

    /**
     * 删除员工
     * @return
     */
/*    @DeleteMapping
    public Result delete(Integer[] ids){
        log.info("删除员工：{}", Arrays.toString(ids));
        return Result.success();
    }*/
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除员工：{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /**
     * 路径参数要绑定方法形参必须加上注解@PathVariable
     * @return
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer  id){
        log.info("根据ID查询员工信息：{}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    /**
     * 更新员工信息
     * @return
     */
    @PutMapping
    public Result update (@RequestBody Emp emp){
        log.info("修改员工：{}", emp);
        empService.update(emp);
        return Result.success();
    }
}
