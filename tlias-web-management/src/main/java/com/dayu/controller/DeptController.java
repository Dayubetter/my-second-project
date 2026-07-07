package com.dayu.controller;

import com.dayu.anno.Log;
import com.dayu.pojo.Dept;
import com.dayu.pojo.Result;
import com.dayu.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
// @RequestMapping("/depts") 加类上面以后，方法自动拼接请求路径
@RestController
public class DeptController {

    // private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

//    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping("/depts")
    public Result list(){
        System.out.println("查询全部的部门数据");
        log.info("查询全部的部门数据：");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

//    /**
//     * 删除部门 - 方式一：使用HttpServletRequest获取请求参数
//     */
//    @DeleteMapping("/depts")
//    public Result delete(HttpServletRequest  request) {
//        String idStr = request.getParameter("id");
//        int id = Integer.parseInt(idStr);
//        System.out.println("根据ID删除部门： " +  id);
//        return Result.success();
//    }

//    /**
//     * 删除部门 - 方式二：使用@RequestParam
//     * 注意事项：一旦声明了@RequestParam，该参数在请求时必须传递，如果不传递将会报错，默认required为true
//     */
//    @DeleteMapping("/depts")
//    public Result delete(@RequestParam(value = "id",required = false) Integer id) {
//        System.out.println("根据ID删除部门： " +  id);
//        return Result.success();
//    }

    /**
     * 接收请求参数:DELETE   /depts?id=10
     * 删除部门 - 方式三：如果请求参数名与形参变量名相同，直接定义方法形参即可接收(省略@RequestParam)
     */
    @Log
    @DeleteMapping("/depts")
    public Result delete(Integer id) {
        System.out.println("根据ID删除部门： " +  id);
        log.info("根据ID删除部门： {}" ,  id);
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 添加部门
     */
    @Log
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept) {
        System.out.println("添加部门： " +  dept);
        log.info("添加部门： {}" ,  dept);
        deptService.add(dept);
        return Result.success();
    }
//    /**
//     * 根据ID查询部门
//     */
//    @GetMapping("/depts/{id}")
//    public Result getInfo(@PathVariable("id") Integer deptId){
//        System.out.println("查询部门ID： " +  deptId);
//        return Result.success();
//    }

    /**
     * 根据ID查询部门
     * 路径参数的参数名和方法的形参名称一致，可以省略
     */
    @GetMapping("/depts/{id}")
    public Result getInfo(@PathVariable Integer id){
        System.out.println("查询部门ID： " +  id);
        log.info("查询部门ID： {}" ,  id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    /**
     * 修改部门
     */
    @Log
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
        System.out.println("修改部门： " +  dept);
        log.info("修改部门： {}" ,  dept);
        deptService.update(dept);
        return Result.success();
    }
}
