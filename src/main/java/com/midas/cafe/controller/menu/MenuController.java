package com.midas.cafe.controller.menu;

import com.midas.cafe.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/menu")
public class MenuController {

  @Autowired
  private MenuService menuService;

  @GetMapping()
  public ModelAndView viewMenu() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("menus", menuService.findAllMenu());
    modelAndView.setViewName("/menu/menu");
    return modelAndView;
  }
}
