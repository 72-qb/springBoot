package com.hqyj.javaSpringBoot.modules.test.service.Impl;

import com.hqyj.javaSpringBoot.modules.test.dao.CountryDao;
import com.hqyj.javaSpringBoot.modules.test.pojo.Country;
import com.hqyj.javaSpringBoot.modules.test.service.CountryService;
import com.hqyj.javaSpringBoot.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/11 16:25
 */
@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryDao countryDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Country getCountryByCountryId(int countryId) {
        return countryDao.getCountryByCountryId(countryId);
    }

    @Override
    public Country getCountryByCountryName(String countryName) {
        return countryDao.getCountryByCountryName(countryName);
    }

    @Override
    public Country mograteCountryByRedis(int countryId) {
        Country country = countryDao.getCountryByCountryId(countryId);

        String countryKey = String.format("country%d", countryId);

/*        redisTemplate.opsForValue().set(countryKey,country);

        return (Country) redisTemplate.opsForValue().get(countryKey);*/
        redisUtils.set(countryKey, country);
        return (Country)redisUtils.get(countryKey);
    }
}
