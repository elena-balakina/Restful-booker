package com.restfulbooker.api;

import com.restfulbooker.api.api.AuthApi;
import com.restfulbooker.api.payloads.Auth;
import io.restassured.response.Response;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ApiAuthTest {

    @Test
    public void authApiShouldReturnToken() {
        Auth auth = new Auth.Builder()
                .setUsername("admin")
                .setPassword("password123")
                .build();
        Response response = AuthApi.postAuth(auth);
        Approvals.verify(response.getStatusCode());
        String actualToken = response.getBody().jsonPath().get("token").toString();
        Assertions.assertFalse(actualToken.isEmpty());
    }
}
