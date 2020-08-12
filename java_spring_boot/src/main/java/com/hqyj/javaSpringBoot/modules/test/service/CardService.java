package com.hqyj.javaSpringBoot.modules.test.service;

import com.hqyj.javaSpringBoot.modules.test.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.test.pojo.Card;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/12 20:00
 */
public interface CardService {

    Result<Card> insertCard(Card card);
}
