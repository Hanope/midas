package com.midas.cafe.repository.admin;

import com.midas.cafe.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * User: kimkm
 * Date: 2018-05-26
 * Time: 오후 3:27
 */
@Repository
public class GroupDao
{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int insert(Group group)
	{
		String query = "INSERT INTO mi_group (name) VALUES (?)";
		return jdbcTemplate.update(query, group.getName());
	}

	public int delete(Group group)
	{
		String query = "DELETE FROM mi_group WHERE code = ? ";
		return jdbcTemplate.update(query, group.getCode());
	}

	public int update(Group group)
	{
		String query = "UPDATE mi_group SET name = ? WHERE code = ? ";
		return jdbcTemplate.update(query, group.getName(), group.getCode());
	}
}
