package com.dmt.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;


//需要查询数据库，并得到正确的数据
@Component
public class shiroRealm extends AuthorizingRealm {

    //如果数据库中没有数据，返回Null.如果是正确的，返回用户对象
    //token就是要认证的
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        /**
         * 1.将token转换为UserNamePassWord
         * 2.获取用户名
         * 3.查询数据库
         * 4.查询到了需要封装信息，返回给调用者
         * 5.没查到，抛出异常，用户未找到
         * */
        SimpleAuthenticationInfo info = null;
        SimpleAuthenticationInfo info1 = null;

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        ByteSource salt = new ByteSource.Util().bytes(username);
            //Class.forName("oracle.jdbc.driver.OracleDriver");
            //String url = "jdbc:oracle:thin:@localhost:1521:orcl";
            try {
               /* Connection conn = DriverManager.getConnection(url,"duan","duan");
                PreparedStatement ps = conn.prepareStatement("select uname,upass from userinfo where uname=?");
                ps.setString(1,username);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    Object priciple = username;
                    Object credentials = rs.getString(3);
                    String realName = this.getName();
                    info = new SimpleAuthenticationInfo(priciple,credentials,realName);
                }else{
                    throw new AuthenticationException("用户名或密码不正确");
                }*/
               System.out.println("shiroRealm is in..");
                Object priciple = username;
                Object credentials ="aaa";
                String realName = this.getName();
                SimpleHash au_pass = new SimpleHash("md5",credentials,salt,1024);
                info = new SimpleAuthenticationInfo(priciple,au_pass,realName);
                info1 = new SimpleAuthenticationInfo(priciple,au_pass,salt,realName);
            } catch (Exception e){
                e.printStackTrace();
            }


        return info1;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /*SimpleAuthenticationInfo info = null;
        SimpleAuthenticationInfo info1 = null;*/
        SimpleAuthorizationInfo info = null;
        //Class.forName("oracle.jdbc.driver.OracleDriver");
        //String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        try {
               /* Connection conn = DriverManager.getConnection(url,"duan","duan");
                PreparedStatement ps = conn.prepareStatement("select uname,upass from userinfo where uname=?");
                ps.setString(1,username);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    Object priciple = username;
                    Object credentials = rs.getString(3);
                    String realName = this.getName();
                    info = new SimpleAuthenticationInfo(priciple,credentials,realName);
                }else{
                    throw new AuthenticationException("用户名或密码不正确");
                }*/
            Set<String> rols = new HashSet<String>();
            info = new SimpleAuthorizationInfo(rols);
            return info;
        } catch (Exception e){
            e.printStackTrace();
        }
        return info;
    }
}
