package com.midas.cafe.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {

  private boolean result;
  private Object message;

  public Result(boolean result, Object message) {
    this.result = result;
    this.message = message;
  }
}
