package com.midas.cafe.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        if (session.getAttribute("login") == null) { // login세션이 없으면
            saveDest(request); // URI값 가져오기
            response.sendRedirect("/user/login");
            session.setAttribute("temp", "temp");
            return false;
        }
        return true;
    }



    private void saveDest(HttpServletRequest req) {
        String uri = req.getRequestURI(); // URI로부터 URL 반환
        String query = req.getQueryString(); // URI 반환(질의 문자열 포함)

        if (query == null || query.equals("null"))
            query = "";
        else
            query = "?" + query;

        if (req.getMethod().equals("GET"))
            req.getSession().setAttribute("dest", uri + query);
    }
}
