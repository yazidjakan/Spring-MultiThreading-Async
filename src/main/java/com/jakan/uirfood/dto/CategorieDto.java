package com.jakan.uirfood.dto;

import com.jakan.uirfood.bean.Repas;

import java.util.List;

public record CategorieDto(
        String id,
        String nom,
        List<Repas> repasList
) {
}
