package org.qingqiao.vhr.controller.system.basic;

import org.qingqiao.vhr.bean.Department;
import org.qingqiao.vhr.service.DeptService;
import org.qingqiao.vhr.utils.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @GetMapping("/dept")
    public List<Department> getAllDept() {
        return deptService.getAllDept();
    }

    @PostMapping("/dept")
    public ResponseBean insterDept(@RequestBody Department department) {
        if (deptService.insterDept(department) > 0) {
            return ResponseBean.ok("添加成功");
        }
        return ResponseBean.error("添加失败");
    }
    @DeleteMapping("/dept/{id}")
    public ResponseBean deleteDept(@PathVariable Integer id){
        return deptService.deleteDept(id);
    }
}
