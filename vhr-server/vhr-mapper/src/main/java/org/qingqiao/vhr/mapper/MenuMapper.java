package org.qingqiao.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.qingqiao.vhr.bean.Menu;
import org.qingqiao.vhr.bean.Role;

import java.util.List;
@Mapper
public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> getMenuByHrId(Integer id);

    List<Menu> getAllMenusByRid();

    List<Menu> getAllMenus();

    List<Menu> getAllMenusByRID(@Param("rid") Integer rid);
}