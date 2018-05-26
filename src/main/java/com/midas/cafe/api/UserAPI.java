package com.midas.cafe.api;

import com.midas.cafe.model.LoginVO;
import com.midas.cafe.model.Result;
import com.midas.cafe.service.UserService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserAPI {

  @Autowired
  private UserService userService;

  @RequestMapping("/notification")
  public Result getNotification(HttpSession session) {
    LoginVO user = (LoginVO)session.getAttribute("login");
    return userService.getNotification(user);
  }
}
