package com.hqyj.javaSpringBoot.modules.account.service;

import com.github.pagehelper.PageInfo;
import com.hqyj.javaSpringBoot.modules.account.pojo.Resource;
import com.hqyj.javaSpringBoot.modules.account.pojo.Role;
import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;
import com.sun.org.apache.regexp.internal.RE;

import java.util.List;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/25 19:42
 */
public interface ResourceService {

    List<Resource> getResources();

    PageInfo<Resource> getResourcesBySearchVo(SearchVo searchVo);

    Result<Resource> insertResource(Resource resource);

    List<Resource> getResourcesByRoleId(int roleId);
}
