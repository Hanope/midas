package com.midas.cafe.repository.dept;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DeptDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<Map<String, Object>> findAllDept() {
    String query = "SELECT * FROM MI_GROUP";
    return jdbcTemplate.queryForList(query);
  }
}
