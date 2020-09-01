package com.hqyj.springcloud.springCloudClientAccount.modules.account.service;



import com.github.pagehelper.PageInfo;
import com.hqyj.springcloud.springCloudClientAccount.modules.account.pojo.User;
import com.hqyj.springcloud.springCloudClientAccount.modules.common.vo.Result;
import com.hqyj.springcloud.springCloudClientAccount.modules.common.vo.SearchVo;
import org.springframework.web.multipart.MultipartFile;

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

    PageInfo<User> getUserBySearchVo(SearchVo searchVo);

    Result<User> updateUser(User user);

    Result<Object> deleteUser(int userId);

    User getUserByUserId(int userId);

    Result<String> uploadUserImg(MultipartFile file);

    User getUserByUserName(String userName);

    void logout();

    Result<User> comfirmPassword(User user);

    Result<User> updatePassword(User user);
}
