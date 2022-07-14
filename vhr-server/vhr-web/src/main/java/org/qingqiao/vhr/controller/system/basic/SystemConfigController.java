package org.qingqiao.vhr.controller.system.basic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.qingqiao.vhr.bean.Menu;
import org.qingqiao.vhr.service.MenuService;
import org.qingqiao.vhr.utils.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/config")
public class SystemConfigController {

    @Autowired
    private MenuService menuService;

    @GetMapping("menus")
    public String getMenuByHrId() throws JsonProcessingException {
        List<Menu> list = menuService.getMenuByHrId();
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseBean ok = ResponseBean.ok("菜单加载成功!", list);
        String s = objectMapper.writeValueAsString(ok);
        return s;
    }
}
