package com.application.controller;

import com.application.bean.Employee;
import com.application.bean.EmployeeExample;
import com.application.mapper.EmployeeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 阿毛
 * @date 2021/8/12 17:42
 */
@RestController
public class EmployeeController {

    @Resource
    EmployeeMapper employeeMapper;

    //查询所有员工带部门
    @GetMapping("/selectEmpWithDept/{pn}")
    public PageInfo selectEmpWithDept(@PathVariable("pn") Integer pn) {
        PageHelper.startPage(pn, 5);
        List<Employee> employees = employeeMapper.selectWithDeptByExample(null);
        PageInfo pageInfo = new PageInfo(employees, 5);
        return pageInfo;
    }

    //查询所有员工不带部门
    @GetMapping("/emp")
    public String selectEmp(@RequestParam("empName") String empName) {
        if (empName == "") {
            return "员工名不能为空！";
        } else {
            List<Employee> employees = employeeMapper.selectByExample(null);
            for (Employee employee : employees) {
                if (employee.getEmpName().equals(empName)) {
                    return "抱歉！该员工名已存在，请换一个试试。";
                }
            }
        }
        return "恭喜！该员工名可以注册/修改。";
    }

    //查询指定员工带部门
    @GetMapping("/emp/{empId}")
    public Employee selectEmpById(@PathVariable("empId") Integer empId) {
        return employeeMapper.selectWithDeptByPrimaryKey(empId);
    }

    //添加员工
    @PostMapping("/emp")
    public String insertEmp(Employee employee) {
        int i = employeeMapper.insertSelective(employee);
        if (i > 0) {
            return "添加成功！";
        } else {
            return "添加失败！";
        }
    }

    //修改指定员工
    @PutMapping("/emp")
    public String updateEmpById(Employee employee) {
        int i = employeeMapper.updateByPrimaryKeySelective(employee);
        if (i > 0) {
            return "修改成功！";
        } else {
            return "修改失败！";
        }
    }

    //删除指定员工
    @DeleteMapping("/emp/{empId}")
    public String deleteEmpById(@PathVariable("empId") Integer empId) {
        int i = employeeMapper.deleteByPrimaryKey(empId);
        if (i > 0) {
            return "删除成功！";
        } else {
            return "删除失败！";
        }
    }

    //批量删除员工
    @DeleteMapping("/batchDeleteEmp/{ids}")
    public String BatchDeleteEmp(@PathVariable("ids") String ids) {
        if (ids != "") {
            String[] split = ids.split(",");
            int i = 0;
            if (split.length == 1) {
                int empId = Integer.parseInt(split[0]);
                i = employeeMapper.deleteByPrimaryKey(empId);
            } else {
                List<Integer> list = new ArrayList<>();
                for (String s : split) {
                    int empId = Integer.parseInt(s);
                    list.add(empId);
                }
                EmployeeExample example = new EmployeeExample();
                EmployeeExample.Criteria criteria = example.createCriteria();
                criteria.andEmpIdIn(list);
                i = employeeMapper.deleteByExample(example);
            }
            if (i > 0) {
                return "删除成功！";
            } else {
                return "删除失败！";
            }
        } else {
            return "删除失败！员工Id不能为空";
        }
    }

    //根据员工名进行模糊查询带部门
    @GetMapping("/likeSearchEmpWithDept/{likeEmpName}")
    public PageInfo likeSearchEmpWithDept(@PathVariable("likeEmpName") String likeEmpName, @RequestParam("pn") Integer pn) {
        System.out.println(likeEmpName);
        System.out.println(pn);
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        likeEmpName = "%" + likeEmpName + "%";
        criteria.andEmpNameLike(likeEmpName);
        PageHelper.startPage(pn, 5);
        List<Employee> employees = employeeMapper.selectWithDeptByExample(example);
        PageInfo pageInfo = new PageInfo(employees, 5);
        return pageInfo;
    }
}
