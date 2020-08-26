package com.hqyj.javaSpringBoot.modules.account.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.javaSpringBoot.modules.account.dao.ResourceDao;
import com.hqyj.javaSpringBoot.modules.account.dao.RoleResourceDao;
import com.hqyj.javaSpringBoot.modules.account.pojo.Resource;
import com.hqyj.javaSpringBoot.modules.account.pojo.Role;
import com.hqyj.javaSpringBoot.modules.account.service.ResourceService;
import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/25 19:42
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private RoleResourceDao roleResourceDao;

    @Override
    public List<Resource> getResources() {
        return Optional.ofNullable(resourceDao.getResources())
                .orElse(Collections.emptyList());
    }

    @Override
    public PageInfo<Resource> getResourcesBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<Resource>(Optional.ofNullable(resourceDao.getResourcesBySearchVo(searchVo))
        .orElse(Collections.emptyList()));
    }

    @Override
    public Result<Resource> insertResource(Resource resource) {
        Resource resourceTemp=resourceDao.getResourceByPermission(resource.getPermission());
        if(resourceTemp!=null && resourceTemp.getResourceId()!=resource.getResourceId()){
            return new Result<Resource>(Result.ResultStatus.FATLD.status,"Resource permission is repeat.");
        }
        if(resource.getResourceId()>0){
            resourceDao.updateResource(resource);
        }else {
            resourceDao.insertResource(resource);
        }
        roleResourceDao.deletRoleResourceByResourceId(resource.getResourceId());
        if (resource.getRoles() != null && !resource.getRoles().isEmpty()) {
            for (Role role : resource.getRoles()) {
                roleResourceDao.addRoleResource(role.getRoleId(), resource.getResourceId());
            }
        }
        return new Result<Resource>(Result.ResultStatus.SUCCESS.status,"success",resource);
    }

    @Override
    public List<Resource> getResourcesByRoleId(int roleId) {
        return resourceDao.getResourcesByRoleId(roleId);
    }

    @Override
    public Resource getResourceByResourceId(int resourceId) {
        return resourceDao.getResourceByResourceId(resourceId);
    }

    @Override
    public Result<Resource> deleteResourceByResourceId(int resourceId) {
        roleResourceDao.deletRoleResourceByResourceId(resourceId);
        resourceDao.deleteResourceByResourceId(resourceId);
        return new Result<Resource>(Result.ResultStatus.SUCCESS.status,"delete success.0");
    }
}
