package com.midas.cafe.service;

import com.midas.cafe.common.StrUtil;
import com.midas.cafe.model.Result;
import com.midas.cafe.model.User;
import com.midas.cafe.model.UserReservation;
import com.midas.cafe.repository.menu.MenuDao;
import com.midas.cafe.repository.user.UserDao;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: kimkm
 * Date: 2018-05-26
 * Time: 오후 4:01
 */
@Service
public class UserService
{
	@Autowired
	private UserDao userDao;

	public int joinUser(User user){
		return userDao.insertUser(user);
	}

	public void cancelReservation(UserReservation reservation)
	{
		userDao.updateUserCancel(reservation);
	}

	public List<UserReservation> getAllReservation(String loginID)
	{
		return userDao.selectReservation(loginID);
	}

	public Result getAllReservationDetail(String reservationCode) {
		List<Map<String,Object>> list = userDao.selectReservationDetail(reservationCode);
		return new Result(true, list);
	}

	public void addReservation(String loginID, String reserveDt, String description, List<String> detail)
	{
		userDao.insertReservation(loginID, reserveDt, description);
		Integer reservIdx = userDao.getLastInsertID();
		for(String str : detail)
		{
			List<String> list = StrUtil.splitToList(str, ":");
			String code = list.get(0);
			String amount = list.get(1);
			userDao.insertReservationDetail(reservIdx, code, amount);
		}
	}

	public List<Map<String, Object>> findAllUser() {
		return userDao.findAllUser();
	}
}
