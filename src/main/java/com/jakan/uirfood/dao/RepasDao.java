package com.jakan.uirfood.dao;

import com.jakan.uirfood.bean.Repas;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepasDao extends MongoRepository<Repas, String> {
}
