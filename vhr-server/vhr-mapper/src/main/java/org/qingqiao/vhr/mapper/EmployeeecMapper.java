package org.qingqiao.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.qingqiao.vhr.bean.Employee;
import org.qingqiao.vhr.bean.Employeeec;

import java.util.List;

@Mapper
public interface EmployeeecMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employeeec record);

    int insertSelective(Employeeec record);

    Employeeec selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employeeec record);

    int updateByPrimaryKey(Employeeec record);

}