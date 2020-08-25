package com.hqyj.javaSpringBoot.modules.account.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/25 22:18
 */
@Repository
@Mapper
public interface RoleResourceDao {

    @Insert("insert role_resource(role_id, resource_id) value(#{roleId}, #{resourceId})")
    void addRoleResource(@Param("roleId") int roleId, @Param("resourceId") int resourceId);

    @Delete("delete from role_resource where resource_id = #{resourceId}")
    void deletRoleResourceByResourceId(int resourceId);
}


