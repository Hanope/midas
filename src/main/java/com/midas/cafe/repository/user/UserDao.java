package com.midas.cafe.repository.user;

import com.midas.cafe.model.Reservation;
import com.midas.cafe.model.User;
import com.midas.cafe.model.UserReservation;
import org.springframework.beans.factory.annotation.Autowired;
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

	public int insertUser(User user){
		String sql="INSERT INTO mi_user (loginid,pwd,name,email,mobile,create_dt,birth,group_code)" +
				"VALUES(?,?,?,?,?,?,?,?)";
		System.out.println("유저:"+user.getName());
		return jdbcTemplate.update(sql,user.getId(),user.getPassword(),user.getName(),
				user.getEmail(),user.getPhone(),new Date(),user.getBirthday(),user.getGroupCode());
	}

	public String selectPwById(String id){
		String sql="select pwd from mi_user where loginid = ?";
		String pwd=jdbcTemplate.queryForObject(sql,new Object[]{id},String.class);
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
				tmp.setRegDate(rs.getString("create_dt"));
				tmp.setPhone(rs.getString("mobile"));
				tmp.setGroupCode(rs.getInt("group_code"));
				return tmp;
			}
				});
		return user;
	}

	public int updateUser(User user){
		String sql="UPDATE mi_user SET name = ?, pwd = ?, mobile = ?," +
				"email = ?, birth = ?  WHERE loginid = ? ";
		int result=jdbcTemplate.update(sql, user.getName(), user.getPassword(),
				user.getPhone(),user.getEmail(),user.getBirthday(),user.getId());
		return result;
	}

	public List<UserReservation> selectReservation(String loginID)
	{
		String sql = "select code, loginid, create_dt, reserve_dt, status, description, end_date, adm_cancel_rs, usr_cancel_rs from mi_rsr where login_id = ?  ";
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
