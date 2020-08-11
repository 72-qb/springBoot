package com.hqyj.javaSpringBoot.modules.test.service;

import com.github.pagehelper.PageInfo;
import com.hqyj.javaSpringBoot.modules.test.common.vo.SearchVo;
import com.hqyj.javaSpringBoot.modules.test.pojo.City;

import java.util.List;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/11 16:24
 */
public interface CityService {

    List<City> getCityByCountryId(int countryId);

    PageInfo<City> getCityPageBySearchVo(int countryId,SearchVo searchVo);
}