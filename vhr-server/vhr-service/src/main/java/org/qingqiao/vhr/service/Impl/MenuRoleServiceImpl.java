package org.qingqiao.vhr.service.Impl;

import org.qingqiao.vhr.mapper.MenuRoleMapper;
import org.qingqiao.vhr.service.MenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuRoleServiceImpl implements MenuRoleService {

    @Autowired
    private MenuRoleMapper menuRoleMapper;


    @Override
    public boolean insertMenuRole(Integer rid, Integer[] mids) {
//        List<MenuRole> list = menuRoleMapper.queryall(rid);
//        if(list.size()!=0){
//            int deleteResult = menuRoleMapper.deleteMenuRole(rid);
//            return deleteResult > 0;
//        }
//        if (list.size()==0){
//            int insertResult = menuRoleMapper.insertMenuRole(rid, mids);
//            return insertResult > 0 ;
//        }
//        return true;
        int deleteResult = menuRoleMapper.deleteMenuRole(rid);
        int insertResult = menuRoleMapper.insertMenuRole(rid, mids);
        return deleteResult > 0 & insertResult > 0;
    }
}
