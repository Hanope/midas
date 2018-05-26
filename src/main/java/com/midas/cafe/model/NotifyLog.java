package com.midas.cafe.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotifyLog {

  private int idx;
  private String userId;
  private String notificationType;
  private String target;
  private String body;
}
