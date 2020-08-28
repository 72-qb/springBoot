package com.hqyj.javaSpringBoot.modules.account.service;

import com.github.pagehelper.PageInfo;
import com.hqyj.javaSpringBoot.modules.account.pojo.Role;
import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;

import java.util.List;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/22 17:11
 */
public interface RoleService {

    List<Role> getRoles();

    PageInfo<Role> getRolesBySearchVo(SearchVo searchVo);

    Result<Role> insertRole(Role role);

    Role getRolesByRoleId(int roleId);

    Result<Role> deleteRoleByRoleId(int roleId);

    Result<Role> updateRole(Role role);
}
