package com.midas.cafe.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCoupon {

  private String userId;
  private String couponCode;
  private Date issueDate;
  private Date useDate;
  private String reason;
}
