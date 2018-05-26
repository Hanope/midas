package com.midas.cafe.controller.admin;

import com.midas.cafe.model.CafeMenu;
import com.midas.cafe.repository.category.CategoryDao;
import com.midas.cafe.repository.menu.MenuDao;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/menu")
public class MenuController {

  @Autowired
  private CategoryDao categoryDao;

  @Autowired
  private MenuDao menuDao;

  @GetMapping("/add")
  public ModelAndView addMenuView() {
    ModelAndView modelAndView = new ModelAndView();
    List<Map<String, Object>> categories = categoryDao.findAllCategories();

    modelAndView.addObject("categories", categories);
    modelAndView.setViewName("/admin/cafe_menu");
    return modelAndView;
  }

  @GetMapping()
  public String menusView() {
    return "/admin/adminMenuManage";
  }

  @PostMapping()
  public String addMenu(CafeMenu menu) {
    menuDao.addMenu(menu);
    return "/admin/adminMenuManage";
  }
}
