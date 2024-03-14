package com.jakan.uirfood.dto;

public record UserDto(
        String id,
        String nom,
        String prenom,
        String email,
        String password,
        String fonction
) {
}
