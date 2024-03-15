package com.jakan.uirfood.dto;

import com.jakan.uirfood.enums.Fonction;

public record UserDto(
        String id,
        String nom,
        String prenom,
        String email,
        String password,
        Fonction fonction
) {
}
