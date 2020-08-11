package com.hqyj.javaSpringBoot.modules.test.controller;

import com.github.pagehelper.PageInfo;
import com.hqyj.javaSpringBoot.modules.test.common.vo.SearchVo;
import com.hqyj.javaSpringBoot.modules.test.pojo.City;
import com.hqyj.javaSpringBoot.modules.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;


/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/11 16:25
 */
@RestController
@RequestMapping("/cc")
public class CityController {

    @Autowired
    private CityService cityService;
    /*
    * localhost/cc/city/522   ---->get方法
    * */
    @GetMapping("/city/{countryId}")
    public List<City> getCityByCountryId(@PathVariable int countryId){
        return cityService.getCityByCountryId(countryId);
    }

    /*
    * localhost/cc/city/522   --------->post方法
    *{"currentPage":"1","pageSize":"5"}
    * */
    @PostMapping(value = "/city/{countryId}",consumes = "application/json")
    public PageInfo<City> getCityPageBySearchVo(@PathVariable int countryId, @RequestBody SearchVo searchVo){
        return cityService.getCityPageBySearchVo(countryId,searchVo);
    }
}
