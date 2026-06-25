package com.dayu.mapper;

import com.dayu.pojo.EmpExpr;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 员工工作经历Mapper
 */
@Mapper
public interface EmpExprMapper {

    /**
     * 批量保存员工工作经历
     * @param empList
     */
    void insertBatch(List<EmpExpr> empList);

    /**
     * 根据员工Id批量删除员工工作经历
     * @param ids
     */
    void deleteByEmpIds(List<Integer> empIds);
}
