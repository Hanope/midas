package com.midas.cafe.api.admin;

import com.midas.cafe.model.LoginVO;
import com.midas.cafe.model.Result;
import com.midas.cafe.service.ReservationService;
import com.midas.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * User: kimkm
 * Date: 2018-05-27
 * Time: 오전 12:34
 */
@RestController
@RequestMapping("/api")
public class ReservationAPI
{
	@Autowired
	private UserService userService;

	@Autowired
	private ReservationService reservationService;

	@GetMapping("/reservation/{reservationCode}")
	public Result findAllReservationDetail(@PathVariable String reservationCode){
		return userService.getAllReservationDetail(reservationCode);
	}

	@PutMapping("/admin/reservation/{reservationCode}")
	public Result updateStatus(@PathVariable int reservationCode, @RequestParam("reservStatus") int reservStatus) {
		return reservationService.updateStatus(reservationCode, reservStatus);
	}

	@GetMapping("/reservation/notifyoff")
	public Result notifyOff(HttpSession session)
	{
		LoginVO login = (LoginVO) session.getAttribute("login");
		String loginID = login.getId();
		return userService.notifyOff(loginID);
	}
}
