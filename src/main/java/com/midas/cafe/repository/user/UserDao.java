package com.midas.cafe.repository.user;

import com.midas.cafe.model.CafeMenu;
import com.midas.cafe.model.Reservation;
import com.midas.cafe.model.UserReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: kimkm
 * Date: 2018-05-26
 * Time: 오후 4:01
 */
@Repository
public class UserDao
{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<UserReservation> selectReservation(String loginID)
	{
		String sql = "select code, loginid, create_dt, reserve_dt, status, description, end_date, adm_cancel_rs, usr_cancel_rs from mi_rsr where login_id = ?  ";
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql, loginID);
		List<UserReservation> reservationList = new ArrayList<>();
		for(Map<String,Object> map : list)
			reservationList.add(new UserReservation(map));
		return reservationList;
	}

	public int insertReservation(UserReservation reservation)
	{
		String sql = "INSERT INTO mi_rsr (loginid, create_dt, reserve_dt, status, description) VALUES (?,now(),?,?,?)";
		return jdbcTemplate.update(sql, reservation.getUserId(), reservation.getReserveDt(), reservation.getStatus().getCode(), reservation.getDescription());
	}

	public int updateUserCancel(UserReservation reservation)
	{
		String sql = "UPDATE mi_rsr SET status = ?, usr_cancel_rs = ? WHERE code = ? ";
		return jdbcTemplate.update(sql, reservation.getStatus().getCode(), reservation.getUserCancelDesc());
	}
}