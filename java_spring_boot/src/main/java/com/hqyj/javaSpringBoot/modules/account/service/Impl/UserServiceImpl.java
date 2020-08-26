package com.hqyj.javaSpringBoot.modules.account.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.javaSpringBoot.config.ResourceConfigBean;
import com.hqyj.javaSpringBoot.modules.account.dao.UserDao;
import com.hqyj.javaSpringBoot.modules.account.dao.UserRoleDao;
import com.hqyj.javaSpringBoot.modules.account.pojo.Role;
import com.hqyj.javaSpringBoot.modules.account.pojo.User;
import com.hqyj.javaSpringBoot.modules.account.service.UserService;
import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;
import com.hqyj.javaSpringBoot.utils.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Optionals;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sun.security.provider.MD5;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.security.Security;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
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
    private UserRoleDao userRoleDao;
    @Autowired
    private ResourceConfigBean resourceConfigBean;

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
        user.setPassword(MD5Util.getMD5(user));
        user.setCreateDate(LocalDateTime.now());
        userDao.registerUser(user);
        List<Role> roles = user.getRoles();
        if (roles != null && !roles.isEmpty()) {
            roles.stream().forEach(item -> {
                userRoleDao.insertUserRole(user.getUserId(), item.getRoleId());
            });
        }
        return new Result<User>(Result.ResultStatus.SUCCESS.status, "register success.", user);
    }

    @Override
    public Result<User> loginUserByUserName(User user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUserName(),
                MD5Util.getMD5(user));
        usernamePasswordToken.setRememberMe(user.getRememberMe());
        try {
            subject.login(usernamePasswordToken);
            subject.checkRoles();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<User>(Result.ResultStatus.FATLD.status, "UserName or password error");
        }
        Session session=subject.getSession();
        session.setAttribute("user",subject.getPrincipal());
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
        List<Role> roles = user.getRoles();
        if (roles != null && !roles.isEmpty()) {
            userRoleDao.deleteUserRoleByUserId(user.getUserId());
            roles.stream().forEach(item -> {
                userRoleDao.insertUserRole(user.getUserId(), item.getRoleId());
            });
        }
        return new Result<User>(Result.ResultStatus.SUCCESS.status, "update success", user);
    }

    @Override
    @Transactional
    public Result<Object> deleteUser(int userId) {
        userDao.deleteUser(userId);
        userRoleDao.deleteUserRoleByUserId(userId);
        return new Result<>(Result.ResultStatus.SUCCESS.status, "delete success.");
    }

    @Override
    public User getUserByUserId(int userId) {
        return userDao.getUserByUserId(userId);
    }

    @Override
    public Result<String> uploadUserImg(MultipartFile file) {
        if (file.isEmpty()) {
            return new Result<String>(Result.ResultStatus.FATLD.status, "Please select Img");
        }
        String relativePath = "";
        String filePath = "";
        try {
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
        }

        return new Result<String>(Result.ResultStatus.SUCCESS.status, "Upload success", relativePath);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    @Override
    public void logout() {
        Subject subject=SecurityUtils.getSubject();
        subject.logout();
        Session session=subject.getSession();
        session.setAttribute("user",null);
    }
}
