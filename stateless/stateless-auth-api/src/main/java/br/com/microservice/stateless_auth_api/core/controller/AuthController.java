package br.com.microservice.stateless_auth_api.core.controller;

import br.com.microservice.stateless_auth_api.core.dto.AuthRequest;
import br.com.microservice.stateless_auth_api.core.dto.TokenDto;
import br.com.microservice.stateless_auth_api.core.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public TokenDto tokenValidate(@RequestHeader String accessToken) {
        return authService.validateToken(accessToken);
    }
}
