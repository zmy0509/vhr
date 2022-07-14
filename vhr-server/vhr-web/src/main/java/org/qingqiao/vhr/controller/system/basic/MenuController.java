package org.qingqiao.vhr.controller.system.basic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.qingqiao.vhr.bean.Menu;
import org.qingqiao.vhr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/basic")
public class MenuController {
    @Autowired
    private MenuService menuService;


    @GetMapping("/menus")
    public String getAllMenus() throws JsonProcessingException {
        List<Menu> menus = menuService.getAllMenus();
        return new ObjectMapper().writeValueAsString(menus);
    }

    @GetMapping("/menus/{rid}")
    public String getAllMenusByRID(@PathVariable Integer rid) throws JsonProcessingException {
        List<Menu> menus = menuService.getAllByRID(rid);
        return new ObjectMapper().writeValueAsString(menus);
    }

}
