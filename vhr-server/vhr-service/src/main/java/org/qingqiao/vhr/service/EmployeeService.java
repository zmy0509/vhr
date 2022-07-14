package org.qingqiao.vhr.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.ibatis.annotations.Param;
import org.qingqiao.vhr.bean.*;
import org.qingqiao.vhr.utils.ResponseBean;

import java.util.List;

public interface EmployeeService {
    ResponsePageBean getAllEmps(Integer page, Integer size, String name);

    int addRmp(Employee employee);

    int updateRmp(Employee employee);

    int deleteEmp(Integer id);

    int deleteManyEmp(@Param("ids") Integer[] ids);

    List<Nation> getAllNation();

    List<Department> getAllDepartment();

    List<Position> getAllPosition();

    List<JobLevel> getAllJobLevel();

    List<Politicsstatus> getAllPoliticsstatus();

    Integer MaxWorkID();

    Employee getEmpById(Integer id);
}
