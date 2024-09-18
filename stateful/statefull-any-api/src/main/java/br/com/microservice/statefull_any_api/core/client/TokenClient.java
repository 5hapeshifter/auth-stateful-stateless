package br.com.microservice.statefull_any_api.core.client;

import br.com.microservice.statefull_any_api.core.dto.AuthUserResponse;
import br.com.microservice.statefull_any_api.core.dto.TokenDto;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/api/auth")
public interface TokenClient {

    @PostExchange("/token/validate")
    TokenDto validateToken(@RequestHeader String accessToken);

    @GetExchange("/user")
    AuthUserResponse getAuthenticatedUser(@RequestHeader String accessToken);

}
