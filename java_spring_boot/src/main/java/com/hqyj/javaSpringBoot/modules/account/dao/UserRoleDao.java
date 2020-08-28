package com.hqyj.javaSpringBoot.modules.account.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/21 16:11
 */
@Repository
@Mapper
public interface UserRoleDao {

    @Delete("delete from user_role where user_id=#{userId}")
    void deleteUserRoleByUserId(int userId);

    @Delete("delete from user_role where role_id=#{roleId}")
    void deleteUserRoleByRoleId(int roleId);

    @Insert("insert into user_role (user_id,role_id) values (#{userId},#{roleId})")
    void insertUserRole(int userId,int roleId);
}
