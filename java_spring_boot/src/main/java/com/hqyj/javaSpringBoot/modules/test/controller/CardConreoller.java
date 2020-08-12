package com.hqyj.javaSpringBoot.modules.test.controller;

import com.hqyj.javaSpringBoot.modules.test.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.test.pojo.Card;
import com.hqyj.javaSpringBoot.modules.test.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/12 20:03
 */
@RestController
@RequestMapping("/cdc")
public class CardConreoller {
    @Autowired
    private CardService cardService;

    /*
    * localhost/cdc/card  -------->post方法
    * {"cardNo":"card56564176464"}
    * */
    @PostMapping(value = "/card",consumes = "application/json")
    public Result<Card> insertCard(@RequestBody Card card) {
        return cardService.insertCard(card);
    }
}
