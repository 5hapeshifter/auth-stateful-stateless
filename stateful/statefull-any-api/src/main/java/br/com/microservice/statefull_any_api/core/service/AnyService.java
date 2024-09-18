package br.com.microservice.statefull_any_api.core.service;

import br.com.microservice.statefull_any_api.core.dto.AnyResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AnyService {

    private final TokenService tokenService;

    public AnyResponse getData(String accessToken) {
        tokenService.validateToken(accessToken);
        var authUser = tokenService.getAuthenticatedUser(accessToken);
        return new AnyResponse(HttpStatus.OK.name(), HttpStatus.OK.value(), authUser);
    }
}
