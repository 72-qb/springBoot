package com.hqyj.springcloud.springCloudClientTest.modules.account.service;

import com.github.pagehelper.PageInfo;
import com.hqyj.springcloud.springCloudClientTest.modules.account.pojo.City;
import com.hqyj.springcloud.springCloudClientTest.modules.common.vo.Result;
import com.hqyj.springcloud.springCloudClientTest.modules.common.vo.SearchVo;


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

    PageInfo<City> getCityPageBySearchVo(int countryId, SearchVo searchVo);

    PageInfo<City> getCitiesSearchVo(SearchVo searchVo);

    Result<City> insertCity(City city);

    Result<City> updateCity(City city);

    Result<Object> deleteCity(int cityId);

}
