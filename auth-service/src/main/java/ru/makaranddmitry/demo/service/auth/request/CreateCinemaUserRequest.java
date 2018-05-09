package ru.makaranddmitry.demo.service.auth.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateCinemaUserRequest implements Serializable {

    private String username;
    private String password;
    private String email;

}
