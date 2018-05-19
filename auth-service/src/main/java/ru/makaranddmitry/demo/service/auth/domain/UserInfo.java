package ru.makaranddmitry.demo.service.auth.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -7442439650668571793L;

    private String username;
    private String email;
    private String role;
    private String firstName;
    private String lastName;
    private Boolean sex;

}
