package com.midas.cafe.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Reservation {

  private int idx;
  private String code;
  private String menuCode;
  private int amount;

  public Reservation(Map<String,Object> map)
  {
    this.idx = (int) map.get("idx");
    this.code = (String) map.get("code");
    this.menuCode = (String) map.get("menucode");
    this.amount = (int) map.get("amount");
  }
}
