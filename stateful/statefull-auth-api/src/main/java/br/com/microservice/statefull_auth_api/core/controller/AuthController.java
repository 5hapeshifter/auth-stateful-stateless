package br.com.microservice.statefull_auth_api.core.controller;

import br.com.microservice.statefull_auth_api.core.dto.AuthRequest;
import br.com.microservice.statefull_auth_api.core.dto.AuthUserResponse;
import br.com.microservice.statefull_auth_api.core.dto.TokenDto;
import br.com.microservice.statefull_auth_api.core.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public TokenDto login(@RequestBody AuthRequest authRequest) {
        return authService.login(authRequest);
    }

    @PostMapping("/token/validate")
    public TokenDto validateToken(@RequestHeader String accessToken) {
        return authService.validateToken(accessToken);
    }

    @PostMapping("/logout")
    public HashMap<String, Object> logout(@RequestHeader String accessToken) {
        authService.logout(accessToken);
        var response = new HashMap<String, Object>();
        response.put("status", HttpStatus.OK.name());
        response.put("code", HttpStatus.OK.value());
        return response;
    }

    @GetMapping("/user")
    public AuthUserResponse getAuthenticatedUser(@RequestHeader String accessToken){
        return authService.getAuthenticatedUser(accessToken);
    }
}
