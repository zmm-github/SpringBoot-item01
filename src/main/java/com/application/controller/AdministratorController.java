package com.application.controller;

import com.application.bean.Administrator;
import com.application.bean.AdministratorExample;
import com.application.mapper.AdministratorMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 阿毛
 * @date 2021/8/11 23:40
 */
@Controller
public class AdministratorController {

    @Resource
    AdministratorMapper administratorMapper;

    //根据用户名和密码查询管理员，用于检查数据库中是否含有该管理员
    @PostMapping("/checkAdministrator")
    public String checkAdministrator(String username, String password) {
        AdministratorExample example = new AdministratorExample();
        AdministratorExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(password);
        List<Administrator> administrators = administratorMapper.selectByExample(example);
        if (administrators != null && administrators.size() > 0) {
            System.out.println("登录成功！");
            return "empList";
        } else {
            System.out.println("登录失败！");
            return "login";
        }
    }

    //根据id查询指定管理员
    @GetMapping("/selectAdminById/{id}")
    @ResponseBody
    public Administrator selectAdministratorById(@PathVariable("id") Integer id) {
        Administrator administrator = administratorMapper.selectByPrimaryKey(id);
        return administrator;
    }
}
