package com.midas.cafe.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Reservation {

  private int idx;
  private String code;
  private String menuCode;
  private int amount;
}
