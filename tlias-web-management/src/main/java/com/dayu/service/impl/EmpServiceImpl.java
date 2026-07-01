package com.dayu.service.impl;

import com.dayu.mapper.EmpExprMapper;
import com.dayu.mapper.EmpMapper;
import com.dayu.pojo.*;
import com.dayu.service.EmpLogService;
import com.dayu.service.EmpService;
import com.dayu.utils.JwtUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;


    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        // 1。根据id修改员工的基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        // 2. 根据id修改员工的工作经历信息
        // a. 先删除
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        // b. 添加
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }

    }

    /**
     * 员工登录
     * @param emp
     * @return
     */
    @Override
    public LoginInfo login(Emp emp) {
        // 1.调用map接口，根据用户名或密码查询员工信息
        Emp e = empMapper.selectByNameAndPwd(emp);
        // 2.判断是否存在这个员工，如果存在，组装登录信息
        if (e != null){
            log.info("登录成功：{}", e);
            // 生成Jwt令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            String jwt = JwtUtils.generateToken(claims);
            return new LoginInfo(e.getId(), e.getUsername(), e.getName(),jwt);
        }
        // 如果不存在，返回null
        return null;
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    /**
     * 批量删除员工
     * 一次删除，多次操作数据库，要么同时成功，要么同时失败，使用事务
     * @param ids
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        // 1，删除员工的基本信息
        empMapper.deleteByIds(ids);
        // 2.删除员工工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    /**
     * 原始分页查询
     * @param page
     * @param pageSize
     * @return
     */
    /*@Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        // 1.调用mapper接口，查询总记录数
        Long total = empMapper.count();
        // 2.调用mapper接口，查询分页数据
        Integer start = (page - 1) * pageSize;
        List<Emp> rows = empMapper.list(start, pageSize);
        // 3.组装PageResult
        return new PageResult<Emp>(total, rows);
    }*/


    /**
     * 使用PageHelper分页查询
     * @param //page 页码
     * @param //pageSize 每页记录数
     * @return
     */
    /*@Override
    public PageResult<Emp> page(Integer page, Integer pageSize, String  name, Integer gender, LocalDate begin, LocalDate end) {
        // 1.设置分页参数()
        PageHelper.startPage(page, pageSize);
        // 2.执行查询
        List<Emp> empList = empMapper.list( name,gender,begin,end);
        // 3.封装PageResult
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }*/

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        // 1.设置分页参数()
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        // 2.执行查询
        List<Emp> empList = empMapper.list(empQueryParam);
        // 3.封装PageResult
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }

    @Transactional(rollbackFor = {Exception.class}) // 事务管理 -默认出现运行时异常时，回滚   ()设置哪些异常回滚
    @Override
    public void save(Emp emp) {
        try {
            // 1.保存员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            // 2.保存员工工作经历信息
            List<EmpExpr> empList = emp.getExprList();
            if (!CollectionUtils.isEmpty(empList)){
                // 遍历集合，为empId赋值
                empList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(empList);
            }
        } finally {
            // 记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工：" + emp);
            empLogService.insertLog(empLog);
        }
    }
}
