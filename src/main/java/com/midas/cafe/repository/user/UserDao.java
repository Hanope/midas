package com.midas.cafe.repository.user;

import com.midas.cafe.model.Reservation;
import com.midas.cafe.model.User;
import com.midas.cafe.model.UserReservation;
import com.midas.cafe.model.enumelem.ReservationStatus;
import java.util.Date;
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

	public int insertUser(User user){
		String sql="INSERT INTO mi_user (loginid,pwd,name,email,mobile,create_dt,birth,group_code)" +
				"VALUES(?,?,?,?,?,?,?,?)";
		System.out.println("유저:"+user.getName());
		return jdbcTemplate.update(sql,user.getId(),user.getPassword(),user.getName(),
				user.getEmail(),user.getPhone(),new Date(),user.getBirthday(),user.getGroupCode());
	}

	public List<UserReservation> selectReservation(String loginID)
	{
		String sql = "select code, loginid, create_dt, reserve_dt, status, description, end_date, adm_cancel_rs, usr_cancel_rs from mi_rsr where loginid = ?  ORDER BY create_dt DESC";
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql, loginID);
		List<UserReservation> reservationList = new ArrayList<>();
		for(Map<String,Object> map : list)
		{
			UserReservation reservation = new UserReservation(map);
			String subSql = "select idx, code, menucode, amount from mi_rsr_detail where code = ?";
			List<Map<String,Object>> subList = jdbcTemplate.queryForList(subSql, reservation.getCode());
			for (Map<String,Object> subMap : subList)
			{
				Reservation res = new Reservation(subMap);
				reservation.addReservation(res);
			}
			reservationList.add(reservation);
		}
		return reservationList;
	}

	public List<Map<String,Object>> selectReservationDetail(String reserveCode)
	{
		String sql = "select c.name category_name, b.name cafe_name, b.price, a.amount from mi_rsr_detail a, mi_cafe_menu b, mi_category c where a.menucode = b.code and b.category_code = c.code and a.code = ?";
		return jdbcTemplate.queryForList(sql, reserveCode);
	}

	public int insertReservation(String loginID, String reserveDate, String description)
	{
		String sql = "INSERT INTO mi_rsr (loginid, create_dt, reserve_dt, status, description) VALUES (?,now(),str_to_date(?,'%Y-%m-%d'),?,?)";
		return jdbcTemplate.update(sql, loginID, reserveDate, ReservationStatus.REQUEST.getCode(), description);
	}

	public int insertReservationDetail(Integer code, String menuCode, String amount)
	{
		String sql = "INSERT INTO mi_rsr_detail (code, menucode, amount) values (?,?,?)";
		return jdbcTemplate.update(sql, code, menuCode, amount);
	}

	public Integer getLastInsertID()
	{
		String sql = "SELECT LAST_INSERT_ID()";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public int updateUserCancel(UserReservation reservation)
	{
		String sql = "UPDATE mi_rsr SET status = ?, usr_cancel_rs = ? WHERE code = ? ";
		return jdbcTemplate.update(sql, reservation.getStatus().getCode(), reservation.getUserCancelDesc());
	}

	public List<Map<String, Object>> findAllUser() {
		String query = "SELECT u.loginid as id, u.name, u.mobile, u.email, u.create_dt, u.birth,  g.name as depart  FROM MI_USER as u INNER JOIN mi_group g ON group_code = g.code";
		return jdbcTemplate.queryForList(query);
	}
}
