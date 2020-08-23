package com.hqyj.javaSpringBoot.modules.account.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.javaSpringBoot.modules.account.dao.RoleDao;
import com.hqyj.javaSpringBoot.modules.account.pojo.Role;
import com.hqyj.javaSpringBoot.modules.account.service.RoleService;
import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/22 17:11
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> getRoles() {
        return Optional.ofNullable(roleDao.getRoles())
                .orElse(Collections.emptyList());
    }

    @Override
    public PageInfo<Role> getRolesBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<Role>(Optional
                .ofNullable(roleDao.getRolesBySearchVo(searchVo))
                .orElse(Collections.emptyList()));
    }

    @Override
    @Transactional
    public Result<Role> insertRole(Role role) {
        if (role.getRoleName().isEmpty()){
            return new Result<>(Result.ResultStatus.FATLD.status,"Please input roleName");
        }
        Role roleTemp=roleDao.getRoleByRoleName(role.getRoleName());
        if(roleTemp!=null){
            return new Result<>(Result.ResultStatus.FATLD.status,"roleName is repeat");
        }
        roleDao.insertRole(role);
        return new Result<>(Result.ResultStatus.SUCCESS.status,"insert success");
    }

    @Override
    public Role getRolesByRoleId(int roleId) {
        return roleDao.getRolesByRoleId(roleId);
    }

    @Override
    @Transactional
    public Result<Role> deleteRoleByRoleId(int roleId) {
        roleDao.deleteRoleByRoleId(roleId);
        return new Result<Role>(Result.ResultStatus.SUCCESS.status,"delete success");
    }
}
