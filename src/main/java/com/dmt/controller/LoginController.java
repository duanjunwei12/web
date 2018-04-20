package com.dmt.controller;

import com.dmt.entity.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login(UserInfo userInfo){
        String username = userInfo.getUsername();
        String password = userInfo.getPassword();
        //创建Subject实例
        Subject currentUser = SecurityUtils.getSubject();
        //Session session = currentUser.getSession();
        //判断如何是未登录状态，将用户信息封装进Token
        if(currentUser.isAuthenticated() == false){
            UsernamePasswordToken token  = new UsernamePasswordToken(username,password);
            token.setRememberMe(true);
            try{
                currentUser.login(token);//完成登陆认证，登陆认证的底层实现是通过Realm，Realm在application.xml中的安全管理器中配置，我们自己的逻辑
                //这个token实际上传递到了自定义的shiroRealm的doxxx方法中了
            }catch(AuthenticationException e){//认证失败，用户名密码错误
                return "login";
            }
        }
        return "index";
    }
    @RequestMapping("/logout")
    public String logout(){
        return "login";
    }
}
