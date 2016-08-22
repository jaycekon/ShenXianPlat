package com.Shop.Interceptor;

import com.Shop.Util.UserAgentUtil;
import com.Shop.beans.User;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016/7/27.
 */
public class RequestInterceptor implements HandlerInterceptor {
    Logger logger = Logger.getLogger(this.getClass());
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = UserAgentUtil.isMobileDevice(request);
        System.out.println(flag);
//        if(!flag){
////            response.setHeader("Access-Control-Allow-Origin", "*");
////            response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS");
////            response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
//
//            response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
//            response.addHeader("Access-Control-Allow-Methods","*");
//            response.addHeader("Access-Control-Max-Age","100");
//            response.addHeader("Access-Control-Allow-Headers", "Content-Type");
//            response.addHeader("Access-Control-Allow-Credentials","true");

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.setHeader("Access-Control-Allow-Credentials","true"); //是否支持cookie跨域
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
