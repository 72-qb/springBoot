package com.hqyj.javaSpringBoot.modules.account.dao;

import com.hqyj.javaSpringBoot.modules.account.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/20 14:07
 */
@Repository
@Mapper
public interface UserDao {

    @Insert("insert into user (user_name,password,user_img,create_date) " +
            "values(#{userName},#{password},#{userImg},#{createDate})")
    @Options(useGeneratedKeys = true,keyColumn = "user_id",keyProperty = "userId")
    void registerUser(User user);

    @Select("select * from user where user_name=#{userName}")
    User getUserByUserName(String userName);

}
