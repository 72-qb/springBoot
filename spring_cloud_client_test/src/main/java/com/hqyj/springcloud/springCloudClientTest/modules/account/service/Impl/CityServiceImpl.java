package com.hqyj.springcloud.springCloudClientTest.modules.account.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.springcloud.springCloudClientTest.modules.account.dao.CityDao;
import com.hqyj.springcloud.springCloudClientTest.modules.account.pojo.City;
import com.hqyj.springcloud.springCloudClientTest.modules.account.service.CityService;
import com.hqyj.springcloud.springCloudClientTest.modules.common.vo.Result;
import com.hqyj.springcloud.springCloudClientTest.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/11 16:24
 */
@Service
public class CityServiceImpl implements CityService {

    @Value("${server.port}")
    private int port;
    @Autowired
    private CityDao cityDao;

    @Override
    public List<City> getCityByCountryId(int countryId) {
        System.out.println("port"+port);
        return Optional.ofNullable(cityDao.getCityByCountryId(countryId))
                .orElse(Collections.emptyList());
    }


    @Override
    public PageInfo<City> getCityPageBySearchVo(int countryId, SearchVo searchVo) {
        /*初始化当前页与记录*/
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<City>(Optional.ofNullable(cityDao.getCityByCountryId(countryId))
                .orElse(Collections.emptyList()));
    }

    @Override
    public PageInfo<City> getCitiesSearchVo(SearchVo searchVo) {
        /*初始化当前页与记录*/
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<City>(Optional
                .ofNullable(cityDao.getCitiesSearchVo(searchVo))
                .orElse(Collections.emptyList()));
    }

    @Override
    @Transactional
    public Result<City> insertCity(City city) {
        city.setDateCreated(new Date());
        cityDao.insertCity(city);
        return new Result<City>(Result.ResultStatus.SUCCESS.status,"insert success",city);
    }

    @Override
    @Transactional(noRollbackFor = ArithmeticException.class)
    public Result<City> updateCity(City city) {
        /*int i=1/0;*/
        cityDao.updateCity(city);
        return new Result<City>(Result.ResultStatus.SUCCESS.status,"update success",city);
    }

    @Override
    @Transactional
    public Result<Object> deleteCity(int cityId) {
        cityDao.deleteCity(cityId);
        return new Result<Object>(Result.ResultStatus.SUCCESS.status,"delete success",cityId);
    }
}
