package com.midas.cafe.controller.user;

import com.midas.cafe.model.User;
import com.midas.cafe.model.UserReservation;
import com.midas.cafe.repository.user.UserDao;
import com.midas.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * User: kimkm
 * Date: 2018-05-26
 * Time: 오후 4:00
 */
@Controller
@RequestMapping("/user")
public class UserController
{
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String loginGet(){return "/user/loginForm";}

	@GetMapping("/join")
	public String joinGet(){ return "/user/joinForm";}

	@PostMapping("/join")
	public String joinPost(User user){
		user.setRegDate(new Date());//등록날짜
		System.out.println(user.toString());
		try{
			userService.joinUser(user);
		}catch(Exception e){
		}
		return "/index";
	}



	@GetMapping("/reservation")
	public String reservationView()
	{
		return "/user/reservation_list";
	}

	@PostMapping("/reservation")
	public String addReservation(UserReservation reservation)
	{
		userDao.insertReservation(reservation);
		return "/user/reservation_list";
	}

	@PostMapping("/reservation/cancel")
	public String cancelReservation(UserReservation reservation)
	{
		userDao.updateUserCancel(reservation);
		return "/user/reservation_list";
	}
}
