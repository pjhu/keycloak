package com.pjhu.keycloak.endpoint;

import com.pjhu.keycloak.service.KeyCloakUser;
import com.pjhu.keycloak.service.KeycloakClient;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "/users", produces = APPLICATION_JSON_UTF8_VALUE)
public class controller {

    private final KeycloakClient keycloakRemoteService;

    public controller(KeycloakClient keycloakRemoteService) {
        this.keycloakRemoteService = keycloakRemoteService;
    }

    @GetMapping
    public List<KeyCloakUser> getUsers() {
        return keycloakRemoteService.getUsers();
    }
}
