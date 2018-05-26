package com.midas.cafe.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginVO {
    private String id;
    private String password;
    private String name;
    private String role;
}
