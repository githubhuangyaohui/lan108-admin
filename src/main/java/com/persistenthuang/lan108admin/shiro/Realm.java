package com.persistenthuang.lan108admin.shiro;

import com.persistenthuang.lan108admin.pojo.User;
import com.persistenthuang.lan108admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class Realm extends AuthorizingRealm {
    @Autowired
    private UserService userService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        SimpleAuthenticationInfo authenticationInfo = null;
        try {
            String userName = token.getPrincipal().toString();
            User user = userService.getAllMessageByName(userName);
            authenticationInfo = new SimpleAuthenticationInfo(userName, user.getUserPassword(), ByteSource.Util.bytes(user.getUserSalt()), getName());
        } catch (Exception e) {
            log.info("Realm验证失败");
        }
        return authenticationInfo;
    }
}
