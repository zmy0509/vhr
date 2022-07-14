package org.qingqiao.vhr.service.Impl;

import org.qingqiao.vhr.bean.*;
import org.qingqiao.vhr.mapper.*;
import org.qingqiao.vhr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private NationMapper nationMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private JobLevelMapper jobLevelMapper;
    @Autowired
    private PoliticsstatusMapper politicsstatusMapper;

    @Override
    public ResponsePageBean getAllEmps(Integer page, Integer size, String name) {
        if(page != null && page != 1){
            page = (page-1)*size;
        }
        Integer total = employeeMapper.getTotal(name);
        List<Employee> allEmps = employeeMapper.getAllEmps(page, size , name);
        ResponsePageBean bean = new ResponsePageBean();
        bean.setData(allEmps);
        bean.setTotal(total);
        return bean;
    }

    @Override
    public int addRmp(Employee employee) {
        return employeeMapper.insertSelective(employee);
    }

    @Override
    public int updateRmp(Employee employee) {
        return employeeMapper.updateByPrimaryKeySelective(employee);
    }

    @Override
    public int deleteEmp(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteManyEmp(Integer[] ids) {
        return employeeMapper.deleteManyEmp(ids);
    }

    @Override
    public List<Nation> getAllNation() {
        return nationMapper.getAllNation();
    }

    @Override
    public List<Department> getAllDepartment() {
        return departmentMapper.getAllDepartment();
    }

    @Override
    public List<Position> getAllPosition() {
        return positionMapper.getAllPosition();
    }

    @Override
    public List<JobLevel> getAllJobLevel() {
        return jobLevelMapper.getAllJobLevel();
    }

    @Override
    public List<Politicsstatus> getAllPoliticsstatus() {
        return politicsstatusMapper.getAllPoliticsstatus();
    }

    @Override
    public Integer MaxWorkID() {
        return employeeMapper.MaxWorkID();
    }

    @Override
    public Employee getEmpById(Integer id) {
        return employeeMapper.getEmpById(id);
    }

}
