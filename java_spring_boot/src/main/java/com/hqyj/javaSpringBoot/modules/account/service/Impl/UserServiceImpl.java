package com.hqyj.javaSpringBoot.modules.account.service.Impl;

import com.hqyj.javaSpringBoot.modules.account.dao.UserDao;
import com.hqyj.javaSpringBoot.modules.account.pojo.User;
import com.hqyj.javaSpringBoot.modules.account.service.UserService;
import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/20 14:17
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Result<User> registerUser(User user) {
        User userTemp=userDao.getUserByUserName(user.getUserName());
        if (userTemp!=null){
           return new Result<User>(Result.ResultStatus.FATLD.status,"userName is repeat");
        }
        user.setPassword(MD5Util.getMD5(user));
        user.setCreateDate(LocalDateTime.now());
        userDao.registerUser(user);
        return new Result<User>(Result.ResultStatus.SUCCESS.status,"register success.",user);
    }

    @Override
    public Result<User> loginUserByUserName(User user) {
        User userTemp=userDao.getUserByUserName(user.getUserName());
        if(userTemp!=null && userTemp.getPassword().equals(MD5Util.getMD5(user))){
            return new Result<User>(Result.ResultStatus.SUCCESS.status,"login success",user);
        }
        return new Result<User>(Result.ResultStatus.FATLD.status,"UserName or password error");
    }
}
