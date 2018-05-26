package com.midas.cafe.controller.admin;

import com.midas.cafe.model.CafeMenu;
import com.midas.cafe.repository.admin.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  private AdminDao adminDao;

  @GetMapping("/menu")
  public String menuView() {
    return "/admin/cafe_menu";
  }

  @PostMapping("/menu")
  public String addMenu(CafeMenu cafeMenu) {
    adminDao.insert(cafeMenu);
    return "/admin/cafe_menu";
  }
}
