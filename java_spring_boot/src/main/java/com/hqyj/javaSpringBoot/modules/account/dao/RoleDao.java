package com.hqyj.javaSpringBoot.modules.account.dao;

import com.hqyj.javaSpringBoot.modules.account.pojo.Role;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/21 16:22
 */
@Repository
@Mapper
public interface RoleDao {

    @Select("select * from role r left join user_role ur on r.role_id=ur.role_id where ur.user_id=#{userId}")
    List<Role> getRoleByUserId(int userId);

    @Select("select * from role where role_name=#{roleName}")
    Role getRoleByRoleName(String roleName);

    @Select("select * from role")
    List<Role> getRoles();

    @Select("<script>"+
            "select * from role "+
            "<where>"+
            "<if test='keyWord!=\"\" and keyWord!=null'>"+
            "and (role_name like '%${keyWord}%')"+
            "</if>"+
            "</where>"+
            "<choose>"+
            "<when test='orderBy !=\"\" and orderBy!=null'>"+
            "order by ${orderBy} ${sort}"+
            "</when>"+
            "<otherwise>"+
            "order by role_id desc"+
            "</otherwise>"+
            "</choose>"+
            "</script>")
    List<Role> getRolesBySearchVo(SearchVo searchVo);

    @Insert("insert into role (role_name) values(#{roleName})")
    void insertRole(Role role);

    @Select("select * from role where role_id=#{roleId}")
    Role getRolesByRoleId(int roleId);

    @Delete("delete from role where role_id=#{roleId}")
    void deleteRoleByRoleId(int roleId);
}
