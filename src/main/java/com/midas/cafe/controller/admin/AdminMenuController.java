package com.midas.cafe.controller.admin;

import com.midas.cafe.model.CafeMenu;
import com.midas.cafe.model.Result;
import com.midas.cafe.repository.category.CategoryDao;
import com.midas.cafe.service.MenuService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/menu")
public class AdminMenuController {

  @Autowired
  private CategoryDao categoryDao;

  @Autowired
  private MenuService menuService;

  @GetMapping("/add")
  public ModelAndView addMenuView() {
    ModelAndView modelAndView = new ModelAndView();
    List<Map<String, Object>> categories = categoryDao.findAllCategories();

    modelAndView.addObject("categories", categories);
    modelAndView.setViewName("/admin/adminRegMenuForm");
    return modelAndView;
  }

  @GetMapping()
  public ModelAndView menusView() {
    ModelAndView modelAndView = new ModelAndView();
    List<Map<String, Object>> menus = menuService.findAllMenu();
    modelAndView.setViewName("/admin/adminMenu");
    modelAndView.addObject("menus", menus);

    return modelAndView;
  }

  @PostMapping()
  public String addMenu(CafeMenu menu, @RequestParam("file")MultipartFile file) {
    menuService.addMenu(menu, file);
    return "redirect:/admin/menu";
  }

  @PostMapping("/modify")
  public String updateMenu(CafeMenu menu, @RequestParam("file")MultipartFile file) {
    menuService.updateMenu(menu, file);
    return "redirect:/admin/menu";
  }
}
