package org.qingqiao.vhr.service.Impl;

import org.qingqiao.vhr.bean.Hr;
import org.qingqiao.vhr.bean.Menu;
import org.qingqiao.vhr.mapper.MenuMapper;
import org.qingqiao.vhr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getMenuByHrId() {
        //获取当前登录的用户
        Hr hr =(Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return menuMapper.getMenuByHrId(hr.getId());
    }

    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

    @Override
    public List<Menu> getAllByRID(Integer rid) {
        return menuMapper.getAllMenusByRID(rid);
    }


}
