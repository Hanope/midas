package com.midas.cafe.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CafeMenu {

  private int code;
  private String name;
  private int price;
  private String categoryCode;
  private int imgNo;
  private char status;

}
