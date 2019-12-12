package com.teamjw.tripapp.app.user.security.auth.ajax;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Ajax 기반 인증
 */

public class LoginRequest {
    private String name;
    private String password;

    @JsonCreator
    public LoginRequest(@JsonProperty("name") String name, @JsonProperty("password") String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
