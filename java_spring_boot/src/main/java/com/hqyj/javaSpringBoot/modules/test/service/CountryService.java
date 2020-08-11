package com.hqyj.javaSpringBoot.modules.test.service;

import com.hqyj.javaSpringBoot.modules.test.pojo.Country;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/11 16:25
 */
public interface CountryService {

    Country getCountryByCountryId(int countryId);

    Country getCountryByCountryName(String countryName);
}
