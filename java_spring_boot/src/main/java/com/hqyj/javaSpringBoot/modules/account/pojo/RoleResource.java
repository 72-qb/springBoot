package com.hqyj.javaSpringBoot.modules.account.pojo;

import javax.persistence.*;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/20 13:30
 */
@Entity
@Table(name = "role_resource")
public class RoleResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleResourceId;
    private int roleId;
    private int resourceId;

    public int getRoleResourceId() {
        return roleResourceId;
    }

    public void setRoleResourceId(int roleResourceId) {
        this.roleResourceId = roleResourceId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}