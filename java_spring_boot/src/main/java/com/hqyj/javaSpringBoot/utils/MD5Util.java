package com.hqyj.javaSpringBoot.utils;

import com.hqyj.javaSpringBoot.modules.account.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/20 16:25
 */
public class MD5Util {

    public static String getMD5(User user){

        if(StringUtils.isBlank(user.getPassword())){
            return null;
        }
        String base=user.getPassword()+"/"+user.getUserName();
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }


}
