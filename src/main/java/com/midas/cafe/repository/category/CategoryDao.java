package com.midas.cafe.repository.category;

import com.midas.cafe.model.MenuCategory;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<Map<String, Object>> findAllCategories() {
    String query = "SELECT * FROM MI_CATEGORY";
    return jdbcTemplate.queryForList(query);
  }

  public int addCategory(MenuCategory category) {
    String query = "INSERT INTO MI_CATEGORY(NAME) VALUES(?)";
    return jdbcTemplate.update(query, category.getName());
  }
}
