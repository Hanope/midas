package com.midas.cafe.api.admin;

import com.midas.cafe.model.Result;
import com.midas.cafe.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CategoryAPI {

  @Autowired
  private CategoryService categoryService;

  @GetMapping("/category")
  public Result findAllCategories() {
    return categoryService.findAllCategories();
  }

  @PostMapping("/admin/category/{name}")
  public Result add(@PathVariable("name") String name) {
    return categoryService.addCategory(name);
  }

}
