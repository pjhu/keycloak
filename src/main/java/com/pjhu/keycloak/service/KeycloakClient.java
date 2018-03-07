package com.pjhu.keycloak.service;

import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeycloakClient {

    @Value("${keycloak.auth-server-url}")
    private String keycloakAuthServerUrl;

    @Autowired
    private KeycloakRestTemplate template;

    @PreAuthorize("hasRole('CLIENT_USER')")
    public List<KeyCloakUser> getUsers() {
        String userUrl = keycloakAuthServerUrl + "/admin/realms/demo/users";
        ResponseEntity<KeyCloakUser[]> response = template.getForEntity(userUrl, KeyCloakUser[].class);
        return Arrays.stream(response.getBody()).collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('CLIENT_USER')")
    public ResponseEntity logout() {
        String userUrl = keycloakAuthServerUrl + "/admin/realms/demo/users/b81efdc3-eec9-4c04-97b4-a54c9c34cd8c/logout";
        return template.postForEntity(userUrl, null, ResponseEntity.class);
    }
}
