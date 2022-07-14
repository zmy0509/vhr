package org.qingqiao.vhr.service;

import org.qingqiao.vhr.bean.Department;
import org.qingqiao.vhr.utils.ResponseBean;

import java.util.List;

public interface DeptService {
    List<Department> getAllDept();

    int insterDept(Department department);

    ResponseBean deleteDept(Integer id);
}
