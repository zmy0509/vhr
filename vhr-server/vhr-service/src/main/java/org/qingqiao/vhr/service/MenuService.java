package org.qingqiao.vhr.service;

import org.apache.ibatis.annotations.Param;
import org.qingqiao.vhr.bean.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> getMenuByHrId();

    List<Menu> getAllMenus();

    List<Menu> getAllByRID(@Param("rid") Integer rid);
}
