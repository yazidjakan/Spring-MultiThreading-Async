package com.jakan.uirfood.dto;

import com.jakan.uirfood.bean.Categorie;

public record RepasDto(
        String id,
        String libelle,
        String description,
        String prix,
        String image,
        String note,
        Categorie categorie
){
}
