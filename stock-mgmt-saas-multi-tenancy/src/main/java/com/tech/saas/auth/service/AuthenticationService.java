package com.tech.saas.auth.service;

import com.tech.saas.auth.requests.LoginRequest;
import com.tech.saas.auth.responses.LoginResponse;

public interface AuthenticationService {

    LoginResponse login(final LoginRequest request);
}
