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

  public int addMenu(CafeMenu menu, int fileIdx) {
    String query = "INSERT INTO MI_CAFE_MENU(name, price, category_code, img_src, status) VALUES(?, ?, ?, ?, '1')";
    return jdbcTemplate.update(query,
        menu.getName(),
        menu.getPrice(),
        menu.getCategoryCode(),
        fileIdx);
  }

  public List<Map<String, Object>> findAllMenus() {
    StringBuilder query = new StringBuilder();
    query.append("SELECT mn.code as menu_code, mn.name as menu_name, mn.price, c.name as category_name, i.file_url FROM  mi_cafe_menu as mn ");
    query.append("LEFT JOIN mi_img as i ");
    query.append("ON mn.img_src = i.fno ");
    query.append("INNER JOIN mi_category c ");
    query.append("ON mn.category_code = c.code");

    return jdbcTemplate.queryForList(query.toString());
  }

  public List<Map<String, Object>> findAllMenusByMenuName(String name) {
    StringBuilder query = new StringBuilder();
    query.append("SELECT mn.code as menu_code, mn.name as menu_name, mn.price, c.name as category_name, i.file_url FROM  mi_cafe_menu as mn ");
    query.append("LEFT JOIN mi_img as i ");
    query.append("ON mn.img_src = i.fno ");
    query.append("INNER JOIN mi_category c ");
    query.append("ON mn.category_code = c.code ");
    query.append("WHERE mn.name = ?");

    return jdbcTemplate.queryForList(query.toString(), name);
  }

  public void updateMenu(CafeMenu menu) {
    String query = "UPDATE mi_cafe_menu SET name = ?, price = ?, img_src = ? WHERE code=?";
    jdbcTemplate.update(query, menu.getName(), menu.getPrice(), menu.getImgNo(), menu.getCode());
  }

  public void deleteByNo(int no) {
    String query = "DELETE FROM mi_cafe_menu WHERE code=?";
    jdbcTemplate.update(query, no);
  }
}
