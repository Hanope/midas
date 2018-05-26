package com.midas.cafe.controller.user;

import com.midas.cafe.common.DateUtil;
import com.midas.cafe.model.User;
import com.midas.cafe.model.UserReservation;
import com.midas.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

	@GetMapping("/login")
	public String loginGet()
	{
		return "/user/loginForm";
	}

	@GetMapping("/join")
	public String joinGet()
	{
		return "/user/joinForm";
	}

	@PostMapping("/join")
	public String joinPost(User user)
	{
		user.setRegDate(DateUtil.currentTimestamp());//등록날짜
		System.out.println(user.toString());
		try
		{
			userService.joinUser(user);
		}
		catch (Exception e)
		{
		}
		return "/index";
	}

	@GetMapping("/reservation")
	public ModelAndView reservationView(@RequestParam String loginID)
	{
		// todo : loginID 세션에서 빼오는 걸로 수정
		ModelAndView modelAndView = new ModelAndView();
		List<UserReservation> list = userService.getAllReservation(loginID);

		modelAndView.addObject("list", list);
		modelAndView.setViewName("/user/reservation_list");
		return modelAndView;
	}

	@GetMapping("/reservation/add")
	public ModelAndView reservationAddView()
	{// todo : loginID 세션에서 빼오는 걸로 수정
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user/reservation_add");
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
