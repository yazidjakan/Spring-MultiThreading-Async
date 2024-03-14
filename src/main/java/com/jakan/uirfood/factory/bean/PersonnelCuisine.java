package com.jakan.uirfood.factory.bean;

import com.jakan.uirfood.bean.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "personnelcuisine")
public class PersonnelCuisine extends User {
    @Id private String numPC;
}
