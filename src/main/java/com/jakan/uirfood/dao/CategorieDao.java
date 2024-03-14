package com.jakan.uirfood.dao;

import com.jakan.uirfood.bean.Categorie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieDao extends MongoRepository<Categorie, String> {
}
