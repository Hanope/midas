package com.midas.cafe.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuCategory {

  private int code;
  private String name;

  public MenuCategory(String name) {
    this.name = name;
  }
}
