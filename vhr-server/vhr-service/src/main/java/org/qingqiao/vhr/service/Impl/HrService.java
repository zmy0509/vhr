package org.qingqiao.vhr.service.Impl;

import org.qingqiao.vhr.bean.Hr;
import org.qingqiao.vhr.mapper.HrMapper;
import org.qingqiao.vhr.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class HrService implements UserDetailsService {
    @Autowired
    private HrMapper hrMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr =(Hr) hrMapper.loadUserByUsername(username);
        if (hr == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        hr.setRoles(roleMapper.getRolesByHrId(hr.getId()));
        return hr;
    }
}
