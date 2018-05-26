package com.midas.cafe.api.admin;

import com.midas.cafe.model.Result;
import com.midas.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping("/reservation/{reservationcode}")
	public Result findAllReservationDetail(@PathVariable String reservationCode){
		return userService.getAllReservationDetail(reservationCode);
	}
}
