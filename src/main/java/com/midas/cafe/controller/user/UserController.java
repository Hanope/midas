package com.midas.cafe.controller.user;

import com.midas.cafe.model.UserReservation;
import com.midas.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	private UserService userService;

	@GetMapping("/reservation")
	public String reservationView()
	{
		return "/user/reservation_list";
	}

	@PostMapping("/reservation")
	public String addReservation(UserReservation reservation)
	{
		userService.addReservation(reservation);
		return "/user/reservation_list";
	}

	@PostMapping("/reservation/cancel")
	public String cancelReservation(UserReservation reservation)
	{
		userService.cancelReservation(reservation);
		return "/user/reservation_list";
	}
}
