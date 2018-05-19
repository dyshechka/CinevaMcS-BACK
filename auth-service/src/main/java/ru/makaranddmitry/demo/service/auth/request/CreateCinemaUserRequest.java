package ru.makaranddmitry.demo.service.auth.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateCinemaUserRequest implements Serializable {

    private static final long serialVersionUID = -589574115119873143L;

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Boolean sex;

}
