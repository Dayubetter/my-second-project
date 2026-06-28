package com.dayu.mapper;

import com.dayu.pojo.Emp;
import com.dayu.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 员工信息
 */
@Mapper
public interface EmpMapper {
    // -----------------------------原始分页查询实现

    /**
     * 查询总记录数
     * @return
     */
//    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
//    public Long count();


    /**
     * 分页查询
     * @return
     */
//    @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id " +
//            "order by e.update_time desc limit #{start},#{pageSize}")
//    public List<Emp> list(Integer start, Integer pageSize);

    // -----------------------------使用PageHelper分页查询实现
    //@Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc")
    //public List<Emp> list(String  name, Integer gender, LocalDate begin, LocalDate end);

    /**
     * 条件分页查询
     * @param empQueryParam
     * @return
     */
    public List<Emp> list(EmpQueryParam empQueryParam);


    /**
     * 保存员工信息
     * @param emp
     */
    // 注意下面要驼峰名
    @Options(useGeneratedKeys = true, keyProperty = "id")  // 获取到生成的主键 - 主键返回
    @Insert("insert into emp(username, password, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
    "values (#{username},#{password},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    /**
     * 根据Id批量删除员工基本信息
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据Id查询员工基本信息以及
     * @param id
     * @return
     */
    Emp getById(Integer id);

    /**
     * 根据Id修改员工基本信息
     * @param emp
     */
    void updateById(Emp emp);

    /**
     * 统计员工职位人数
     * @return
     */
    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    /**
     * 统计员工性别人数
     * @return
     */
    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();
}
