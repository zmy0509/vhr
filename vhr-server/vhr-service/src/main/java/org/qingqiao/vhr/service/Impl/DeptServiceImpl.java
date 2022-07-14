package org.qingqiao.vhr.service.Impl;

import org.qingqiao.vhr.bean.Department;
import org.qingqiao.vhr.mapper.DepartmentMapper;
import org.qingqiao.vhr.service.DeptService;
import org.qingqiao.vhr.utils.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getAllDept() {
        return departmentMapper.getAllDept();
    }

    @Override
    public int insterDept(Department department) {
        departmentMapper.insertSelective(department);
        Department parentDept = departmentMapper.selectByPrimaryKey(department.getParentId());
        department.setDepPath(parentDept.getDepPath() + "." + department.getId());
        int i1 = departmentMapper.updateByPrimaryKeySelective(department);
        parentDept.setParent(true);
        int i = departmentMapper.updateByPrimaryKeySelective(parentDept);
        return i;
    }

    @Override
    public ResponseBean deleteDept(Integer id) {
//        System.out.println("id:"+id);
        List<Department> list = departmentMapper.selectDeptById(id);
//        System.out.println("list"+list);
        List<Department> allDepartmentson = departmentMapper.getAllDepartmentson(id);
//        System.out.println("allDepartmentson"+allDepartmentson);
        if (list.size()==0&&allDepartmentson.size()==0){
            Department allDepartmentfather = departmentMapper.getAllDepartmentfather(id);
            int i = departmentMapper.deleteByPrimaryKey(id);
//            System.out.println(allDepartmentfather);
            Integer id1 = allDepartmentfather.getId();
            List<Department> dept = departmentMapper.getAllDepartmentson(id1);
            if (dept.size()==0){
                allDepartmentfather.setParent(false);
                departmentMapper.updateByPrimaryKeySelective(allDepartmentfather);
            }
//            System.out.println("allDepartmentfather"+allDepartmentfather);
            return ResponseBean.ok("删除成功",null);
        }
        if (list.size()!=0&&allDepartmentson.size()==0){
            return ResponseBean.error("部门有人");
        }
        if (list.size()==0&&allDepartmentson.size()!=0){
            return ResponseBean.error("有子部门");
        }
        return ResponseBean.error("");
    }
}
