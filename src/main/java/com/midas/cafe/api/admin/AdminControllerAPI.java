package com.midas.cafe.api.admin;

import com.midas.cafe.model.MenuCategory;
import com.midas.cafe.repository.admin.AdminDao;
import com.midas.cafe.service.AdminService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminControllerAPI {

  @Autowired
  private AdminService adminService;

  @PostMapping("/category")
  public Map<String, Object> add(@RequestParam String name) {
    return adminService.addCategory(name);
  }
}
