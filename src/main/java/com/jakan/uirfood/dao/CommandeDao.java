package com.jakan.uirfood.dao;

import com.jakan.uirfood.bean.Commande;
import com.jakan.uirfood.dto.RepasDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeDao extends MongoRepository<Commande, String> {
}
