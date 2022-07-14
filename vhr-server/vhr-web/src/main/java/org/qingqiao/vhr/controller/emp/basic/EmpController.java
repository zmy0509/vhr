package org.qingqiao.vhr.controller.emp.basic;

import org.qingqiao.vhr.bean.*;
import org.qingqiao.vhr.service.EmployeeService;
import org.qingqiao.vhr.utils.ResponseBean;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/employee/basic")
public class EmpController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/emp")
    public ResponsePageBean getAllEmps(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "") String name) {

        ResponsePageBean bean = employeeService.getAllEmps(page, size, name);
        return bean;
    }

    @PostMapping("/emp")
    public ResponseBean addEmp(@RequestBody Employee employee) throws ParseException {
       /* //工号
        Integer integer = employeeService.MaxWorkID();
        Integer i = integer+1;
        String format = String.format("%08d",Integer.valueOf(i));
        employee.setWorkID(format);
        //在职状态
        employee.setWorkState("在职");
        //入职日期
        employee.setBeginDate(employee.getBeginContract());
        //转正日期
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String parse = sdf.format(employee.getBeginDate());
        String str= parse;
        Date dt=sdf.parse(str);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.MONTH,3);
        Date dt1=rightNow.getTime();
        String reStr = sdf.format(dt1);
        employee.setConversionTime(reStr);
        //合同期限
        Date endContract = employee.getEndContract();
        Date beginContract = employee.getBeginContract();
        long time = endContract.getTime();
        long time1 = beginContract.getTime();
        long l=time-time1;
        double l1 =(double) l / 1000 / 60 / 60 / 24 / 30 / 12;
        String s1 = String.format("%.2f", l1);
        Double of = Double.valueOf(s1);
        employee.setContractTerm(of);*/

        if (employeeService.addRmp(employee) == 1) {
            //根据id查询emp表所有信息
            Employee e = employeeService.getEmpById(employee.getId());
            rabbitTemplate.convertAndSend("qingqiao.mail.welcome");
            return ResponseBean.ok("添加成功");
        }
        return ResponseBean.error("添加失败");
    }

    @PutMapping("/emp")
    public ResponseBean updateEmp(@RequestBody Employee employee) throws ParseException {
        //入职日期
        employee.setBeginDate(employee.getBeginContract());
        //转正日期
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String parse = sdf.format(employee.getBeginDate());
        String str= parse;
        Date dt=sdf.parse(str);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.MONTH,3);
        Date dt1=rightNow.getTime();
        String reStr = sdf.format(dt1);
        employee.setConversionTime(reStr);
        //合同期限
        Date endContract = employee.getEndContract();
        Date beginContract = employee.getBeginContract();
        long time = endContract.getTime();
        long time1 = beginContract.getTime();
        long l=time-time1;
        double l1 =(double) l / 1000 / 60 / 60 / 24 / 30 / 12;
        String s1 = String.format("%.2f", l1);
        Double of = Double.valueOf(s1);
        employee.setContractTerm(of);
        if (employeeService.updateRmp(employee) == 1) {
            return ResponseBean.ok("修改成功");
        }
        return ResponseBean.error("修改失败");
    }

    @DeleteMapping("/emp/{id}")
    public ResponseBean deleteEmp(@PathVariable Integer id) {
        if (employeeService.deleteEmp(id) == 1) {
            return ResponseBean.ok("删除成功!");
        }
        return ResponseBean.error("删除失败!");
    }

    @DeleteMapping("/emps/{ids}")
    public ResponseBean deleteManyEmp(@PathVariable Integer[] ids) {
        if (employeeService.deleteManyEmp(ids) == ids.length) {
            return ResponseBean.ok("删除成功!");
        }
        return ResponseBean.error("删除失败!");
    }

    @GetMapping("/nation")
    public List<Nation> getAllNation() {
        return employeeService.getAllNation();
    }

    @GetMapping("/department")
    public List<Department> getAllDepartment() {
        return employeeService.getAllDepartment();
    }

    @GetMapping("/postion")
    public List<Position> getAllPosition() {
        return employeeService.getAllPosition();
    }

    @GetMapping("/jobLevel")
    public List<JobLevel> getAllJobLevel() {
        return employeeService.getAllJobLevel();
    }

    @GetMapping("/politicsstatus")
    public List<Politicsstatus> getAllPoliticsstatus() {
        return employeeService.getAllPoliticsstatus();
    }
}
