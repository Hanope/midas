package com.midas.cafe.api.admin;

import com.midas.cafe.model.Result;
import com.midas.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/user")
public class UserAPI {

  @Autowired
  private UserService userService;

  @RequestMapping("/coupon/{userId}")
  public Result usersCoupon(@PathVariable String userId) {
    return new Result(true, userService.findAllUsersCoupon(userId));
  }
}
