package com.midas.cafe.repository.user;

import com.midas.cafe.common.Crypt;
import com.midas.cafe.model.Reservation;
import com.midas.cafe.model.User;
import com.midas.cafe.model.UserReservation;
import com.midas.cafe.model.enumelem.ReservationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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

	public int insertUser(User user) throws Exception
	{
		String sql="INSERT INTO mi_user (loginid,pwd,name,email,mobile,create_dt,birth,group_code)" +
				"VALUES(?,?,?,?,?,?,?,?, )";
		System.out.println("유저:"+user.getName());
		return jdbcTemplate.update(sql, user.getId(), Crypt.encrypt(user.getPassword()), user.getName(),
		                           user.getEmail(), user.getPhone(), new Date(), user.getBirthday(), user.getGroupCode());
	}

	public String selectPwById(String id){
		String sql = "select pwd from mi_user where loginid = ?";
		String pwd = null;
		try {
			pwd = jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
		} catch (EmptyResultDataAccessException e) { }

		return pwd;
	}

	public User selectUserById(String id){
		String sql="select * from mi_user where loginid = ?";
		User user=jdbcTemplate.queryForObject(sql,
				new Object[]{id},
				new RowMapper<User>(){
			public User mapRow(ResultSet rs, int rowCnt) throws SQLException{
				User tmp=new User();
				tmp.setId(rs.getString("loginid"));
				tmp.setPassword(rs.getString("pwd"));
				tmp.setName((rs.getString("name")));
				tmp.setBirthday(rs.getString("birth"));
				tmp.setEmail(rs.getString("email"));
				tmp.setRegDate(rs.getDate("create_dt"));
				tmp.setPhone(rs.getString("mobile"));
				tmp.setGroupCode(rs.getInt("group_code"));
				tmp.setRole(rs.getString("role"));
				return tmp;
			}
				});
		return user;
	}

	public int updateUser(User user) throws Exception
	{
		String sql="UPDATE mi_user SET name = ?, pwd = ?, mobile = ?," +
				"email = ?, birth = ?  WHERE loginid = ? ";
		int result=jdbcTemplate.update(sql, user.getName(), Crypt.encrypt(user.getPassword()),
				user.getPhone(),user.getEmail(),user.getBirthday(),user.getId());
		return result;
	}

	public List<UserReservation> selectReservation(String loginID)
	{
		String sql = "select code, loginid, create_dt, reserve_dt, status, description, end_date, adm_cancel_rs, usr_cancel_rs from mi_rsr where loginid = ?  AND status IN('0','1','2') ORDER BY create_dt DESC";
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

	public List<Map<String,Object>> getCompleteReserveOrder(String loginID)
	{
		String sql = "select d.name category_name, c.name cafe_name, c.price, b.amount\n" +
				"  from mi_rsr a, mi_rsr_detail b, mi_cafe_menu c, mi_category d\n" +
				"where a.status = ? and a.loginid = ?\n" +
				"  and a.code = b.code\n" +
				"  and b.menucode = c.code\n" +
				"  and c.category_code = d.code ";
		return jdbcTemplate.queryForList(sql, ReservationStatus.READY.getCode(),loginID);
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

	public List<Map<String, Object>> findAllUsersCoupon(String userId) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT muc.loginid, muc.coupon_code, muc.issued_dt, mc.name,");
		query.append(" mc.start_date, mc.end_date, mc.discount_per, m.name as menu_name");
		query.append(" FROM midas2.mi_user_coupon as muc");
		query.append(" INNER JOIN mi_coupon as mc");
		query.append(" ON coupon_code = mc.code");
		query.append(" INNER JOIN mi_category m");
		query.append(" ON mc.category_code = m.code");

		return jdbcTemplate.queryForList(query.toString());
	}

	public int delete(String userId) {
		String query = "DELETE FROM mi_user WHERE loginid = ?";
		return jdbcTemplate.update(query, userId);
	}

	public List<Map<String, Object>> findNotification(String id) {
//		todo 기능구현
		return null;
	}

	public int updateUser2(User user) {
		String query = "";

		if (user.getPassword() == null) {
			query = "UPDATE mi_user SET name = ?, mobile = ?, email = ?, group_code = ? WHERE loginid = ?";
			return jdbcTemplate.update(query, user.getName(), user.getPhone(), user.getEmail(), user.getGroupCode(), user.getId());
		} else {
			query = "UPDATE mi_user SET name = ?, pwd = ?, mobile = ?, email = ?, group_code = ? WHERE loginid = ?";
			return jdbcTemplate.update(query, user.getName(), user.getPassword(), user.getPhone(), user.getEmail(), user.getGroupCode(), user.getId());
		}
	}
}
