package com.midas.cafe.controller.admin;

import com.midas.cafe.model.User;
import com.midas.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

  @Autowired
  private UserService userService;

  @GetMapping()
  public ModelAndView viewAllMember() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("/admin/adminUser");
    modelAndView.addObject("users", userService.findAllUser());
    return modelAndView;
  }

  @PostMapping("/update")
  public String update(User user) {
    userService.updateUser(user);
    return "redirect:/admin/user";
  }
}
