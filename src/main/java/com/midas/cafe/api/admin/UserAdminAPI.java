package com.midas.cafe.api.admin;

import com.midas.cafe.model.Result;
import com.midas.cafe.model.User;
import com.midas.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/user")
public class UserAdminAPI {

  @Autowired
  private UserService userService;

  @RequestMapping("/coupon/{userId}")
  public Result usersCoupon(@PathVariable String userId) {
    return new Result(true, userService.findAllUsersCoupon(userId));
  }

  @DeleteMapping("/{userId}")
  public Result userDelete(@PathVariable String userId) {
    userService.delete(userId);
    return new Result(true, userId + "님이 삭제되었습니다.");
  }

  @PutMapping()
  public Result updateUser(@RequestBody User user) {
    userService.updateUser(user);
    return new Result(true, "수정되었습니다.");
  }
}
