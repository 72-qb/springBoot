package com.hqyj.springcloud.springCloudClientAccount.modules.account.service.Impl;

import com.hqyj.springcloud.springCloudClientAccount.modules.account.pojo.City;
import com.hqyj.springcloud.springCloudClientAccount.modules.account.service.TestFeignClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/30 15:36
 */

@Component
public class TestFeignClientFallback implements TestFeignClient {
    @Override
    public List<City> getCitiesByCountryId(int countryId) {
        return new ArrayList<>();
    }
}
