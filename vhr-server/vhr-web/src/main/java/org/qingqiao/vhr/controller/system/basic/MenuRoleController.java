package org.qingqiao.vhr.controller.system.basic;

import org.qingqiao.vhr.service.MenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/basic")
public class MenuRoleController {

    @Autowired
    private MenuRoleService menuRoleService;


    @PostMapping("/menuRole")
    public boolean insertMenuRole(Integer rid,Integer[] mids){
        return menuRoleService.insertMenuRole(rid,mids);
    }
}
