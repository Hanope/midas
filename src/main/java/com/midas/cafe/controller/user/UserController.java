package com.midas.cafe.controller.user;

import com.midas.cafe.model.Result;
import com.midas.cafe.model.UserReservation;
import com.midas.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * User: kimkm
 * Date: 2018-05-26
 * Time: 오후 4:00
 */
@RestController
@RequestMapping("/user")
public class UserController
{
	@Autowired
	private UserService userService;

	@GetMapping("/reservation")
	public ModelAndView reservationView(@RequestParam String loginID)
	{
		ModelAndView modelAndView = new ModelAndView();
		List<UserReservation> list = userService.getAllReservation(loginID);

		modelAndView.addObject("list", list);
		modelAndView.setViewName("/user/reservation_list");
		return modelAndView;
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
