package com.hqyj.javaSpringBoot.modules.account.service;

import com.hqyj.javaSpringBoot.modules.account.pojo.User;
import com.hqyj.javaSpringBoot.modules.common.vo.Result;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/20 14:16
 */
public interface UserService {

    Result<User> registerUser(User user);

    Result<User> loginUserByUserName(User user);
}
