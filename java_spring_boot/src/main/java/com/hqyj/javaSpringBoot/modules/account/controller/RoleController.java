package com.hqyj.javaSpringBoot.modules.account.controller;

import com.github.pagehelper.PageInfo;
import com.hqyj.javaSpringBoot.modules.account.pojo.Role;
import com.hqyj.javaSpringBoot.modules.account.service.RoleService;
import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/22 17:13
 */
@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    public List<Role> getRoles(){
        return roleService.getRoles();
    }

    @PostMapping(value = "/roles",consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<Role> getRolesBySearchVo(@RequestBody SearchVo searchVo){
        return roleService.getRolesBySearchVo(searchVo);
    }

    @PostMapping(value = "/role",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<Role> insertRole(@RequestBody Role role){
        return roleService.insertRole(role);
    }

    @GetMapping("/role/{roleId}")
    public Role getRoleByRoleId(@PathVariable int roleId){
        return roleService.getRolesByRoleId(roleId);
    }

    @DeleteMapping("/role/{roleId}")
    public Result<Role> deleteRoleByRoleId(@PathVariable int roleId){
        return roleService.deleteRoleByRoleId(roleId);
    }
}
