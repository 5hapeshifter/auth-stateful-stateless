package br.com.microservice.statefull_any_api.core.dto;

public record AnyResponse (String status, Integer code, AuthUserResponse authUserResponse) {
}
