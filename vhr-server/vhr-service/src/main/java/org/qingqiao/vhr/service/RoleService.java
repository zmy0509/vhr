package org.qingqiao.vhr.service;

import org.qingqiao.vhr.bean.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    int addRole(Role role);

    int updateRole(Role role);

    int deleteRole(Integer id);
}
