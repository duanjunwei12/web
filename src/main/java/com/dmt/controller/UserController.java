package com.dmt.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by duanjunwei on 2018/4/9.
 */
@Controller
@RequestMapping("ss")
public class UserController implements BaseController {
    Log log = LogFactory.getLog(UserController.class);
    @RequestMapping("/first")
    public String selAll() {
        log.info("controller");
        return "jsp/success";
    }

    @RequestMapping("xx")
    public String selAll(HttpServletRequest request,HttpServletResponse response) {
        String via = request.getHeader("Via");
        String userAgent = request.getHeader("user-agent");
        log.info("via---------->" + via );
        log.info("useragent---------->" + userAgent );
        String URI = request.getRequestURI();
        StringBuffer URL = request.getRequestURL();
        log.info("URI---------->" + URI );//项目路径
        log.info("URL---------->"+URL);//地址栏的全路径
        String sessionID = request.getHeader("JSESSIONID");
        String cookie = request.getHeader("Cookie");
        log.info("sessionID"+ sessionID + "| cookie : "+ cookie);
        return "jsp/success";
    }
}
