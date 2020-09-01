package com.hqyj.springcloud.springCloudClientAccount.modules.account.service.Impl;

import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;
import com.hqyj.springcloud.springCloudClientAccount.modules.account.dao.UserDao;
import com.hqyj.springcloud.springCloudClientAccount.modules.account.pojo.City;
import com.hqyj.springcloud.springCloudClientAccount.modules.account.pojo.User;
import com.hqyj.springcloud.springCloudClientAccount.modules.account.service.TestFeignClient;
import com.hqyj.springcloud.springCloudClientAccount.modules.account.service.UserService;
import com.hqyj.springcloud.springCloudClientAccount.modules.common.vo.Result;
import com.hqyj.springcloud.springCloudClientAccount.modules.common.vo.SearchVo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TestFeignClient testFeignClient;

    @Override
    @Transactional
    public Result<User> registerUser(User user) {
        if (user.getUserName().isEmpty() || user.getPassword().isEmpty()) {
            return new Result<User>(Result.ResultStatus.FATLD.status, "Please input name or password");
        }
        User userTemp = userDao.getUserByUserName(user.getUserName());
        if (userTemp != null) {
            return new Result<User>(Result.ResultStatus.FATLD.status, "userName is repeat");
        }
        /*user.setPassword(MD5Util.getMD5(user));
        user.setCreateDate(LocalDateTime.now());
        userDao.registerUser(user);
        List<Role> roles = user.getRoles();
        if (roles != null && !roles.isEmpty()) {
            roles.stream().forEach(item -> {
                userRoleDao.insertUserRole(user.getUserId(), item.getRoleId());
            });
        }*/
        return new Result<User>(Result.ResultStatus.SUCCESS.status, "register success.", user);
    }

    @Override
    public Result<User> loginUserByUserName(User user) {
     /*   Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUserName(),
                MD5Util.getMD5(user),user.getRememberMe());
        try {
            subject.login(usernamePasswordToken);
            subject.checkRoles();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<User>(Result.ResultStatus.FATLD.status, "UserName or password error");
        }
        Session session=subject.getSession();
        session.setAttribute("user",subject.getPrincipal());*/
        return new Result<User>(Result.ResultStatus.SUCCESS.status, "login success", user);
    }

    @Override
    public PageInfo<User> getUserBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<User>(Optional
                .ofNullable(userDao.getUserBySearchVo(searchVo))
                .orElse(Collections.emptyList()));
    }

    @Override
    @Transactional
    public Result<User> updateUser(User user) {
        User userTemp = userDao.getUserByUserName(user.getUserName());
        if (userTemp != null && userTemp.getUserId() != user.getUserId()) {
            return new Result<User>(Result.ResultStatus.FATLD.status, "userName us repeat");
        }
        userDao.updateUser(user);
        /*List<Role> roles = user.getRoles();
        if (roles != null && !roles.isEmpty()) {
            userRoleDao.deleteUserRoleByUserId(user.getUserId());
            roles.stream().forEach(item -> {
                userRoleDao.insertUserRole(user.getUserId(), item.getRoleId());
            });
        }*/
        return new Result<User>(Result.ResultStatus.SUCCESS.status, "update success", user);
    }

    @Override
    @Transactional
    public Result<Object> deleteUser(int userId) {
        userDao.deleteUser(userId);
        return new Result<>(Result.ResultStatus.SUCCESS.status, "delete success.");
    }

    @Override
    public User getUserByUserId(int userId) {
        User user = userDao.getUserByUserId(userId);
  /*      List<City> cities =Optional.ofNullable(
                restTemplate.getForObject(
                "http://CLIENT-TEST/cc/city/{countryId}", List.class, 522))
                .orElse(Collections.emptyList());*/
        List<City> cities = testFeignClient.getCitiesByCountryId(522);
        user.setCities(cities);
        return user;
    }


    @Override
    public Result<String> uploadUserImg(MultipartFile file) {
        if (file.isEmpty()) {
            return new Result<String>(Result.ResultStatus.FATLD.status, "Please select Img");
        }
        String relativePath = "";
        String filePath = "";
/*        try {
            String osName = System.getProperty("os.name");
            if (osName.toLowerCase().startsWith("win")) {
                filePath = resourceConfigBean.getLocationPathForWindows() + file.getOriginalFilename();
            } else {
                filePath = resourceConfigBean.getLocationPathForLinux() + file.getOriginalFilename();
            }
            relativePath = resourceConfigBean.getRelativePath() + file.getOriginalFilename();
            File destFile = new File(filePath);
            file.transferTo(destFile);
        } catch (IOException ie) {
            ie.printStackTrace();
            return new Result<String>(Result.ResultStatus.FATLD.status, "Upload success failed");
        }*/

        return new Result<String>(Result.ResultStatus.SUCCESS.status, "Upload success", relativePath);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    @Override
    public void logout() {
/*        Subject subject=SecurityUtils.getSubject();
        subject.logout();
        Session session=subject.getSession();
        session.setAttribute("user",null);*/
    }

    @Override
    public Result<User> comfirmPassword(User user) {
        User userTemp = userDao.getUserByUserName(user.getUserName());
  /*      if(userTemp.getPassword().equals(MD5Util.getMD5(user))){
            return new Result<User>(Result.ResultStatus.SUCCESS.status,"原密码正确.");
        }*/
        return new Result<User>(Result.ResultStatus.FATLD.status, "原密码错误");
    }

    @Override
    public Result<User> updatePassword(User user) {
      /*  user.setPassword(MD5Util.getMD5(user));
        userDao.updatePassword(user);*/
        return new Result<User>(Result.ResultStatus.SUCCESS.status, "修改成功.");
    }
}
