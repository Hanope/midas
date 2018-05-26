package com.midas.cafe.controller;

import com.midas.cafe.model.LoginVO;
import com.midas.cafe.service.UserService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/")
    public String index(HttpSession session){
        LoginVO user = (LoginVO) session.getAttribute("login");
        if(user != null) {
            String notiMsg = userService.getCompleteReserveOrderNotifyMessage(user.getId());
            session.setAttribute("notify", notiMsg.equals("") ? null : notiMsg);
        }
        return "/index";
    }
}
