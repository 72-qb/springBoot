package com.hqyj.javaSpringBoot.modules.test.controller;

import com.hqyj.javaSpringBoot.modules.test.pojo.Country;
import com.hqyj.javaSpringBoot.modules.test.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/11 16:25
 */
@RestController
@RequestMapping("/ctc")
public class CountryController {

    @Autowired
    private CountryService countryService;
    /*
    * localhost/ctc/country/522 ---->get方法
    * */
    @GetMapping("/country/{countryId}")
    public Country getCountryByCountryId(@PathVariable int countryId) {
        return countryService.getCountryByCountryId(countryId);
    }
    /*
    * localhost/ctc/country?countryName=China  ----->get方法
    * */
    @GetMapping("/country")
    public Country getCountryByCountryName(@RequestParam String countryName) {
        return countryService.getCountryByCountryName(countryName);
    }

    /*
    * localhost/ctc/redis/country/522  -------get方法
    * */
    @GetMapping("/redis/country/{countryId}")
    public Country mograteCountryByRedis(@PathVariable int countryId) {
        return countryService.mograteCountryByRedis(countryId);
    }

}
