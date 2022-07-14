package org.qingqiao.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.qingqiao.vhr.bean.MenuRole;

import java.util.List;

@Mapper
public interface MenuRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuRole record);

    int insertSelective(MenuRole record);

    MenuRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuRole record);

    int updateByPrimaryKey(MenuRole record);

    int deleteMenuRole(Integer rid);

    int insertMenuRole(@Param("rid") Integer rid,@Param("mids") Integer[] mids);

    List<MenuRole> queryall(@Param("rid") Integer rid);
}