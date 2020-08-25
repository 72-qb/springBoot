package com.hqyj.javaSpringBoot.modules.account.controller;

import com.github.pagehelper.PageInfo;
import com.hqyj.javaSpringBoot.modules.account.pojo.User;
import com.hqyj.javaSpringBoot.modules.account.service.UserService;
import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.Console;
import java.io.File;
import java.io.IOException;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/20 14:03
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> register(@RequestBody User user){
        return userService.registerUser(user);
    }

    @PostMapping(value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> login(@RequestBody User user){
        return userService.loginUserByUserName(user);
    }

    @PostMapping(value = "/users",consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<User> getUserBySearchVo(@RequestBody SearchVo searchVo){
        return userService.getUserBySearchVo(searchVo);
    }

    @PutMapping(value = "/user",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> updateUserByUserId(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/user/{userId}")
    @RequiresPermissions(value = "/api/user")
    public Result<Object> deleteUser(@PathVariable int userId){
        return userService.deleteUser(userId);
    }

    @GetMapping(value = "/user/{userId}")
    public User getUserByUserId(@PathVariable int userId){
      return   userService.getUserByUserId(userId);
    }

    @PostMapping(value = "/userImg",consumes = "multipart/form-data")
    public Result<String> uploadFile(@RequestParam MultipartFile file){
        return userService.uploadUserImg(file);
    }
}
