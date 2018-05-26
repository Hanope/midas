package com.midas.cafe.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FileDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public void addNewFile() {
    String query = "";
  }

}
