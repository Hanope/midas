package com.midas.cafe.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyLog {

  private String idx;
  private String userId;
  private Date buyDate;
  private String buyType;
  private int price;
}
