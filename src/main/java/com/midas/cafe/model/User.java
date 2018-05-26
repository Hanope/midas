package com.midas.cafe.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {

  private String id;
  private String name;
  private String password;
  private String phone;
  private String email;
  private Date regDate;
  private String birthday;
  private String groupCode;

}
