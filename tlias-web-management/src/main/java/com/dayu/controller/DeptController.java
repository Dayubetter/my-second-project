package com.dayu.controller;

import com.dayu.pojo.Dept;
import com.dayu.pojo.Result;
import com.dayu.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

//    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping("/depts")
    public Result list(){
        System.out.println("查询全部的部门数据");
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
    @DeleteMapping("/depts")
    public Result delete(Integer id) {
        System.out.println("根据ID删除部门： " +  id);
        deptService.deleteById(id);
        return Result.success();
    }
}
