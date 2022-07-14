package org.qingqiao.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.qingqiao.vhr.bean.Role;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> getRolesByHrId(Integer hrid);

    List<Role> getAllRoles();
}