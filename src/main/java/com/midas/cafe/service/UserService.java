package com.midas.cafe.service;

import com.midas.cafe.model.UserReservation;
import com.midas.cafe.repository.menu.MenuDao;
import com.midas.cafe.repository.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

	public void addReservation(UserReservation reservation)
	{
		userDao.insertReservation(reservation);
	}
}
