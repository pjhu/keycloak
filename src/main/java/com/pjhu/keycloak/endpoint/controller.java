package com.pjhu.keycloak.endpoint;

import com.pjhu.keycloak.service.KeyCloakUser;
import com.pjhu.keycloak.service.KeycloakClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_UTF8_VALUE)
public class controller {

    private final KeycloakClient keycloakRemoteService;

    public controller(KeycloakClient keycloakRemoteService) {
        this.keycloakRemoteService = keycloakRemoteService;
    }

    @GetMapping("/users")
    public List<KeyCloakUser> getUsers() {
        return keycloakRemoteService.getUsers();
    }

    @GetMapping
    public List<String> home() {
        return Collections.singletonList("Home Page");
    }
}
