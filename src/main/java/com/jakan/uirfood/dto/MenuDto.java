package com.jakan.uirfood.dto;

import com.jakan.uirfood.bean.Categorie;

import java.util.List;

public record MenuDto(
        String id,
        List<Categorie> categories
) {
}
