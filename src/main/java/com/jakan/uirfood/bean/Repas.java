package com.jakan.uirfood.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "repas")
public class Repas {
    @Id private String id;
    private String libelle;
    private String description;
    private String prix;
    private String image;
    private String note;
    private Categorie categorie;
}
