package com.hqyj.javaSpringBoot.modules.account.controller;

import com.github.pagehelper.PageInfo;
import com.hqyj.javaSpringBoot.modules.account.pojo.Resource;
import com.hqyj.javaSpringBoot.modules.account.service.ResourceService;
import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/24 9:03
 */
@RestController
@RequestMapping("/api")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping("/resources")
    public List<Resource> getResources(){
        return resourceService.getResources();
    }

    @PostMapping(value = "/resources",consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<Resource> getResourcesBySearchVo(@RequestBody SearchVo searchVo) {
        return resourceService.getResourcesBySearchVo(searchVo);
    }

    @PostMapping(value = "/resource",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<Resource> insertResource(@RequestBody Resource resource){
        return resourceService.insertResource(resource);
    }

    @PutMapping(value = "/resource",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<Resource> updateResource(@RequestBody Resource resource){
        return resourceService.insertResource(resource);
    }
}
