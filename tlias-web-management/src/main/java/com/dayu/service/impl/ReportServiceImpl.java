package com.dayu.service.impl;

import com.dayu.mapper.EmpMapper;
import com.dayu.pojo.JobOption;
import com.dayu.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public JobOption getEmpJobData() {
        // 1.调用map接口，获取统计数据
        List<Map<String, Object>> list = empMapper.countEmpJobData(); // map: pos=校验主管，num=1
        // 2.组装结果并返回 TODO  这里是如何控制顺序的
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();
        return new JobOption(jobList, dataList);
    }

    /**
     * 统计员工性别人数
     * @return
     */
    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }
}
