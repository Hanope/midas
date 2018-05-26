package com.midas.cafe.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Promotion {

  private String idx;
  private String title;
  private String body;
  private String writeUser;
  private Date startDate;
  private Date endDate;
}
