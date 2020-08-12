package com.hqyj.javaSpringBoot.modules.test.dao;

import com.hqyj.javaSpringBoot.modules.test.pojo.Country;
import org.apache.ibatis.annotations.*;
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
public interface CountryDao {


    @Results(id = "countryIdResults",value = {
            @Result(column = "country_id",property = "countryId"),
            @Result(column = "country_id",property = "cities",
                    javaType = List.class,
                    many = @Many(select = "com.hqyj.javaSpringBoot.modules.test.dao.CityDao.getCityByCountryId"))
    })
    @Select("select * from m_country where country_id=#{countryId}")
    Country getCountryByCountryId(int countryId);


    @Select("select * from m_country where country_name=#{countryName}")
    @ResultMap(value = "countryIdResults")
    Country getCountryByCountryName(String countryName);



}
