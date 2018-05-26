package com.midas.cafe.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coupon {

  private String code;
  private String name;
  private Date startDate;
  private Date endDate;
  private String categoryCode;
  private int discountPercent;

}
