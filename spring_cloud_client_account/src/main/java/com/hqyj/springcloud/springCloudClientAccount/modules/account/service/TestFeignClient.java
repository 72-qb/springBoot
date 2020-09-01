package com.hqyj.springcloud.springCloudClientAccount.modules.account.service;

import com.hqyj.springcloud.springCloudClientAccount.modules.account.pojo.City;
import com.hqyj.springcloud.springCloudClientAccount.modules.account.service.Impl.TestFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.crypto.Cipher;
import java.util.List;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/30 12:11
 */
@FeignClient(value = "CLIENT-TEST",fallback = TestFeignClientFallback.class)
@Primary
public interface TestFeignClient {

    @GetMapping("/cc/city/{countryId}")
    List<City> getCitiesByCountryId(@PathVariable int countryId);
}
