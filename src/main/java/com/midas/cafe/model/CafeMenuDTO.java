package com.midas.cafe.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CafeMenuDTO {

  private int code;
  private String name;
  private int price;
  private String categoryCode;
  private String imgSrc;
  private char status;

}
