package com.midas.cafe.controller.user;

import com.midas.cafe.model.LoginVO;
import com.midas.cafe.model.User;
import com.midas.cafe.model.UserReservation;
import com.midas.cafe.repository.user.UserDao;
import com.midas.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
		try{
			userService.joinUser(user);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return "/index";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session){
		if(session!=null)
			session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/myPage")
	public String myPageGet(Model model, HttpSession session){
		LoginVO login=(LoginVO)session.getAttribute("login");
		User user=userService.selectUserById(login.getId());
		user.setBirthday(user.getBirthday().substring(0,10));
		model.addAttribute("user",user);
		return "/user/myPage";
	}

	@PostMapping("/myPage")
	public String myPagePost(User user,Model model, HttpSession session){
		LoginVO login=(LoginVO)session.getAttribute("login");
		String id=login.getId();
		user.setId(id);
		try{
			userService.updateUserInfo(user);
			model.addAttribute("changeSuccessMsg",Boolean.TRUE);
		}catch(Exception e){
			throw new RuntimeException(e);
		}

		return "/user/myPage";
	}

	@GetMapping("/myReserve")
	public String myReserveGet(){return "/user/myReserve";}

	@PostMapping("/myReserve")
	public String myReservePost(User user){

		return "/user/myReserve";
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
