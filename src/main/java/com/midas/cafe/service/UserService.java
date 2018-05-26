package com.midas.cafe.service;

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

	public void addReservation(UserReservation reservation)
	{
		userDao.insertReservation(reservation);
	}

	public void cancelReservation(UserReservation reservation)
	{
		userDao.updateUserCancel(reservation);
	}

	public List<Map<String, Object>> findAllUser() {
		return userDao.findAllUser();
	}
}
