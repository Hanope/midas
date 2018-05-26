package com.midas.cafe.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

  private String id;
  private String name;
  private String password;
  private String phone;
  private String email;
  private Date regDate;
  private Date birthday;
  private String groupCode;

}
