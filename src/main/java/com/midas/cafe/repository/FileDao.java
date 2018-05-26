package com.midas.cafe.repository;

import com.midas.cafe.model.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class FileDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  public int addNewFile(FileVO file) {
    String query = "INSERT INTO MI_IMG(file_name, file_url) VALUES(:file_name, :file_url)";

    KeyHolder holder = new GeneratedKeyHolder();
    SqlParameterSource parameter = new MapSqlParameterSource()
        .addValue("file_name", file.getFilename())
        .addValue("file_url", file.getUrl());

    jdbcTemplate.update(query, parameter, holder);
    return holder.getKey().intValue();
  }

}
