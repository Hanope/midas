package com.midas.cafe.service;

import com.midas.cafe.model.MenuCategory;
import com.midas.cafe.model.Result;
import com.midas.cafe.repository.category.CategoryDao;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

  @Autowired
  private CategoryDao categoryDao;

  public Result findAllCategories() {
    List<Map<String, Object>> menuCategories = categoryDao.findAllCategories();
    return new Result(true, menuCategories);
  }

  public Result addCategory(String name) {
    MenuCategory category = new MenuCategory(name);
    categoryDao.addCategory(category);

    return new Result(true, category.getName() + "이 추가되었습니다.");
  }
}
