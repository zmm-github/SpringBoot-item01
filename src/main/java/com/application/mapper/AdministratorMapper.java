package com.application.mapper;

import com.application.bean.Administrator;
import com.application.bean.AdministratorExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface AdministratorMapper {
    long countByExample(AdministratorExample example);

    int deleteByExample(AdministratorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Administrator record);

    int insertSelective(Administrator record);

    List<Administrator> selectByExample(AdministratorExample example);

    Administrator selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Administrator record, @Param("example") AdministratorExample example);

    int updateByExample(@Param("record") Administrator record, @Param("example") AdministratorExample example);

    int updateByPrimaryKeySelective(Administrator record);

    int updateByPrimaryKey(Administrator record);
}