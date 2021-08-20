package com.application.controller;

import com.application.bean.Department;
import com.application.mapper.DepartmentMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 阿毛
 * @date 2021/8/13 16:23
 */
@RestController
public class DepartmentController {

    @Resource
    DepartmentMapper departmentMapper;

    //查询所有部门数据
    @GetMapping("/dept")
    public List<Department> selectDept(){
        return departmentMapper.selectByExample(null);
    }
}
