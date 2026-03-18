package com.example.authapi;

import com.example.authapi.dto.AuthResponse;
import com.example.authapi.dto.LoginRequest;
import com.example.authapi.dto.RegisterRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@SpringBootTest
@AutoConfigureRestTestClient
class AuthapiApplicationTests {

    @Autowired
    private RestTestClient client;

    @Test
    void registerAndLoginTest() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("user1");
        registerRequest.setPassword("password123");

        AuthResponse authResponse = client.post()
                .uri("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .body(registerRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody(AuthResponse.class)
                .returnResult().getResponseBody();

        System.out.println(authResponse.getToken());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("user1");
        loginRequest.setPassword("password123");

        AuthResponse loginResponse = client.post()
                .uri("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .body(loginRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody(AuthResponse.class)
                .returnResult().getResponseBody();

        System.out.println(loginResponse.getToken());

    }

}
