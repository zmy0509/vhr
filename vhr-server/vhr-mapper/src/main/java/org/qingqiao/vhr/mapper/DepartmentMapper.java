package org.qingqiao.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.qingqiao.vhr.bean.Department;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> getAllDept();

    List<Department>  selectDeptById(Integer id);

    List<Department> getAllDepartment();

    Department getAllDepartmentfather(@Param("id") Integer id);

    List<Department> getAllDepartmentson(@Param("id") Integer id);
}