package br.com.microservice.statefull_auth_api.core.service;

import br.com.microservice.statefull_auth_api.core.dto.AuthRequest;
import br.com.microservice.statefull_auth_api.core.dto.AuthUserResponse;
import br.com.microservice.statefull_auth_api.core.dto.TokenDto;
import br.com.microservice.statefull_auth_api.core.model.User;
import br.com.microservice.statefull_auth_api.core.repository.UserRepository;
import br.com.microservice.statefull_auth_api.infra.exception.AuthenticationException;
import br.com.microservice.statefull_auth_api.infra.exception.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public TokenDto login(AuthRequest request) {
        var user = findByUsername(request.username());
        var accessToken = tokenService.createToken(user.getUsername());
        validatePassword(request.password(), user.getPassword());
        return new TokenDto(accessToken);
    }

    private void validatePassword(String rawPassword, String encodedPassword) {
        if (ObjectUtils.isEmpty(rawPassword)) {
            throw new ValidationException("The password must be informed.");
        }

        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new ValidationException("The password is incorrect!");
        }
    }

    public TokenDto validateToken(String accessToken) {
        validateExistingToken(accessToken);
        var valid = tokenService.validateAccessToken(accessToken);
        if (valid) {
            return new TokenDto(accessToken);
        }
        throw new AuthenticationException("Invalid token!");

    }

    public AuthUserResponse getAuthenticatedUser(String accessToken) {
        var tokenData = tokenService.getTokenData(accessToken);
        var user = findByUsername(tokenData.username());
        return new AuthUserResponse(user.getId(), user.getUsername());
    }

    public void logout(String accessToken) {
        tokenService.deleteRedisToken(accessToken);
    }

    private User findByUsername(String username) {
        return repository
                .findByUsername(username)
                .orElseThrow(() -> new ValidationException("User not found!"));
    }

    private void validateExistingToken(String accessToken) {
        if (ObjectUtils.isEmpty(accessToken)) {
            throw new ValidationException("The access token must be informed!");
        }
    }
}
