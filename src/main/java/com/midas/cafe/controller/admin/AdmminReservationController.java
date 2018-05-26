package com.midas.cafe.controller.admin;

import com.midas.cafe.service.ReservationService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/reservation")
public class AdmminReservationController {

  @Autowired
  private ReservationService reservationService;

  @GetMapping()
  public ModelAndView viewReservation() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("/admin/reservation/reservation");
    List<Map<String, Object>> reservation = reservationService.findIngReservation();
    modelAndView.addObject("reservation", reservation);
    return modelAndView;
  }

  @GetMapping("/all")
  public ModelAndView viewAllReservation() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("/admin/reservation/all");
    List<Map<String, Object>> reservation = reservationService.findAll();
    modelAndView.addObject("reservation", reservation);
    return modelAndView;
  }
}
