package com.jakan.uirfood.bean;

import com.jakan.uirfood.dto.CategorieDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "menu")
public class Menu {
    @Id private String id;
    private List<Categorie> categories;


}
