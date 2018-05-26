package com.midas.cafe.service;

import com.midas.cafe.model.Result;
import com.midas.cafe.repository.menu.MenuDao;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

  @Autowired
  private MenuDao menuDao;

  public Result findAllMenu() {
    List<Map<String, Object>> menus = menuDao.findAllMenus();
    Result result = new Result(true, menus);
    return result;
  }
}
