package com.hqyj.javaSpringBoot.modules.account.dao;

import com.hqyj.javaSpringBoot.modules.account.pojo.Resource;
import com.hqyj.javaSpringBoot.modules.account.pojo.Role;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/25 19:48
 */
@Repository
@Mapper
public interface ResourceDao {

    @Select("select * from resource")
    List<Resource> getResources();

    @Select("<script>"+
            "select * from resource "+
            "<where>"+
            "<if test='keyWord!=\"\" and keyWord!=null'>"+
            "and (resource_name like '%${keyWord}%')"+
            "</if>"+
            "</where>"+
            "<choose>"+
            "<when test='orderBy !=\"\" and orderBy!=null'>"+
            "order by ${orderBy} ${sort}"+
            "</when>"+
            "<otherwise>"+
            "order by resource_id desc"+
            "</otherwise>"+
            "</choose>"+
            "</script>")
    List<Resource> getResourcesBySearchVo(SearchVo searchVo);

    @Insert("insert into resource (resource_name,permission) values(#{resourceName},#{permission})")
    @Options(useGeneratedKeys = true, keyProperty = "resourceId", keyColumn = "resource_id")
    void insertResource(Resource resource);

    @Select("select * from resource where permission=#{permission}")
    Resource getResourceByPermission(String permission);

    @Update("update resource set resource_name=#{resourceName},resource_uri=#{resourceUri},permission=#{permission}" +
            "where resource_id=#{resourceId}")
    void updateResource(Resource resource);

    @Select("select * from resource resource left join role_resource roleResource on "
            + "resource.resource_id = roleResource.resource_id where roleResource.role_id = #{roleId}")
    List<Resource> getResourcesByRoleId(int roleId);
}
