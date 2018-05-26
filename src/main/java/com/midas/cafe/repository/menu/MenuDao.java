package com.midas.cafe.repository.menu;

import com.midas.cafe.model.CafeMenu;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MenuDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public int addMenu(CafeMenu menu) {
    String query = "INSERT INTO MI_CAFE_MENU(name, price, category_code, img_src, status) VALUES(?, ?, ?, ?, '1')";
    return jdbcTemplate.update(query,
        menu.getName(),
        menu.getPrice(),
        menu.getCategoryCode(),
        menu.getImgSrc());
  }

  public List<Map<String, Object>> findAllMenus() {
    String query = "SELECT * FROM MI_CAFE_MENU";
    return jdbcTemplate.queryForList(query);
  }
}
