package com.midas.cafe.service;

import com.midas.cafe.model.MenuCategory;
import com.midas.cafe.repository.admin.AdminDao;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

  @Autowired
  private AdminDao adminDao;

  public Map<String, Object> addCategory(String name) {
    MenuCategory category = new MenuCategory(name);
    Map<String, Object> result = new HashMap<>();
    result.put("result", true);
    result.put("message", category.getName() + "이 추가되었습니다.");

    return result;
  }
}
