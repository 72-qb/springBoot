package com.hqyj.javaSpringBoot.config.shiro;

import com.hqyj.javaSpringBoot.modules.account.pojo.Resource;
import com.hqyj.javaSpringBoot.modules.account.pojo.Role;
import com.hqyj.javaSpringBoot.modules.account.pojo.User;
import com.hqyj.javaSpringBoot.modules.account.service.ResourceService;
import com.hqyj.javaSpringBoot.modules.account.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/25 13:30
 */
@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private ResourceService resourceService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        User user=(User)principalCollection.getPrimaryPrincipal();
        List<Role> roles=user.getRoles();
        roles.stream().forEach(item->{
            simpleAuthorizationInfo.addRole(item.getRoleName());
            List<Resource> resources=resourceService.getResourcesByRoleId(item.getRoleId());
            if (resources != null && !resources.isEmpty()) {
                resources.stream().forEach(resource -> {
                    simpleAuthorizationInfo.addStringPermission(resource.getPermission());
                });
            }
        });
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName=(String)token.getPrincipal();
        User user=userService.getUserByUserName(userName);
        if(user==null){
             throw new UnknownAccountException("The account do not exit.");
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
    }
}
