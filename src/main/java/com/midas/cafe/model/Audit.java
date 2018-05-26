package com.midas.cafe.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Audit {

  private String auditId;
  private String userId;
  private String title;
  private String detail;
  private String auditType;
  private Date writeDate;
}
