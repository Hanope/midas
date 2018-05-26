package com.midas.cafe.repository.admin;

import com.midas.cafe.model.CafeMenu;
import com.midas.cafe.model.MenuCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public int addCategory(MenuCategory category) {
    String query = "INSERT INTO MI_CATEGORY(NAME) VALUES(?)";
    return jdbcTemplate.update(query, category.getName());
  }

  public int insert(CafeMenu menu) {
    String query = "INSERT INTO mi_cafe_menu(code, name, price, category_code, img_src) VALUES(?, ?, ?, ?, ?)";
    return jdbcTemplate.update(query, menu.getCode(), menu.getName(), menu.getPrice(), menu.getCategoryCode(), menu.getImgSrc());
  }
}
