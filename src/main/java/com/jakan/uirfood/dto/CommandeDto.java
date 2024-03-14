package com.jakan.uirfood.dto;

import com.jakan.uirfood.bean.Repas;
import com.jakan.uirfood.bean.User;

import java.util.List;

public record CommandeDto(
        String id,
        List<Repas> repas,
        User user
) {
}
