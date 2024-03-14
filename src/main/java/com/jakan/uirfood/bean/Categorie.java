package com.jakan.uirfood.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "categorie")
public class Categorie {
    @Id private String id;
    private String nom;
    private List<Repas> repasList;
}
