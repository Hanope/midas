package com.midas.cafe.model;

import com.midas.cafe.model.enumelem.ReservationStatus;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: kimkm
 * Date: 2018-05-26
 * Time: 오후 4:07
 */
@Getter
@Setter
public class UserReservation
{
	private int code; // 예약idx
	private String userId; // 사용자 로그인ID
	private Date createDt; // 생성일
	private Date reserveDt; // 예약일
	private ReservationStatus status; // 상태
	private String description; // 옵션
	private Date endDate; // 완료일자
	private String adminCancelDesc; // 관리자 취소사유
	private String userCancelDesc; // 사용자 취소사유
	private List<Reservation> reservationList; // 예약상세 리스트

	public UserReservation(Map<String,Object> map)
	{
		this.code = Integer.parseInt((String) map.get("code"));
		this.userId = (String) map.get("loginid");
		this.createDt = (Date) map.get("create_dt");
		this.reserveDt = (Date) map.get("reserve_dt");
		this.status = ReservationStatus.getValues((String) map.get("status"));
		this.description = (String) map.get("description");
		this.endDate = (Date) map.get("end_date");
		this.adminCancelDesc = (String) map.get("adm_cancel_rs");
		this.userCancelDesc = (String) map.get("usr_cancel_rs");
	}

	public void addReservation(Reservation reservation)
	{
		if(reservationList == null)
			reservationList = new ArrayList<>();

		reservationList.add(reservation);
	}
}
