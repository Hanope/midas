package com.midas.cafe.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyLogDetail {

  private String idx;
  private String menuCode;
  private String couponCode;
  private int price;
}
