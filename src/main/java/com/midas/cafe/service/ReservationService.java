package com.midas.cafe.service;

import com.midas.cafe.model.Result;
import com.midas.cafe.repository.reservation.ReservationDao;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

  @Autowired
  private ReservationDao reservationDao;

  public List<Map<String, Object>> findIngReservation() {
    return reservationDao.findIngReservation();
  }

  public List<Map<String, Object>> findAll() {
    return reservationDao.findAll();
  }

  public Result updateStatus(int reservationCode, int reservStatus) {
    if (reservStatus == 3)
      reservationDao.createNotification(reservationCode);
    reservationDao.updateStatus(reservationCode, reservStatus);
    return new Result(true, "상태가 변경되었습니다.");
  }
}
