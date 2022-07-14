package org.qingqiao.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.qingqiao.vhr.bean.Employee;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    List<Employee> getAllEmps(@Param("page") Integer page, @Param("size") Integer size,@Param("name") String name);

    Integer getTotal(String name);

    int deleteManyEmp(@Param("ids") Integer[] ids);

    Integer MaxWorkID();

    Employee getEmpById(Integer id);
}