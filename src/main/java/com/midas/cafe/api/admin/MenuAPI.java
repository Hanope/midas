package com.midas.cafe.api.admin;

import com.midas.cafe.model.Result;
import com.midas.cafe.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/menus")
public class MenuAPI {

  @Autowired
  private MenuService menuService;

  @GetMapping()
  public Result findAllMenu() {
    return menuService.findAllMenu();
  }

}
