package com.hqyj.javaSpringBoot.modules.test.dao;

import com.github.pagehelper.PageInfo;
import com.hqyj.javaSpringBoot.modules.test.pojo.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/11 16:12
 */

@Repository
@Mapper
public interface CityDao {

    @Select("select * from m_city where country_id=#{countryId}")
    List<City> getCityByCountryId(int countryId);
}
