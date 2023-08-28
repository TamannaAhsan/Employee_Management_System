package com.example.office_assistant_personal.constant;

import org.springframework.stereotype.Component;

@Component
public class AppConstant {

    public static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5980";

    public static final String ERROR            = "error";

    public static final String FAILED           = "failed";
    public static final String STATUS           = "status";
    public static final String MESSAGE          = "message";

    public static final Integer SC_BAD_REQUEST      = 400;

    public static final Integer SC_OK               = 200;
    public static final String LOGIN_PATH = "/api/v1/auth/login";
    public static final String REGISTRATION_PATH = "/api/v1/auth/register";

    public static final String LOGOUT_PATH = "/api/v1/auth/logout";
    public static final String[] AUTH_WHITELIST = {
            "/api/v1/companies/**",
            "/api/v1/leaveApplication/**",
            "/api/v1/leaveConfig/**",
            "/api/v1/users/**",
            "/api/v1/users/**",
            "/swagger-ui.html",
            "/swagger-ui/**"
    };
}
