package com.project.seguridad.dto.user;

import lombok.Data;

/**
 * Created by Elí.Arizpe on 01/04/2020.
 */

@Data
public class UserInDto {
    private String email;
    private String password;
    private String name;
    private String lastName;
}
