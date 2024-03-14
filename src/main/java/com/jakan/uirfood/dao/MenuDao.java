package com.jakan.uirfood.dao;

import com.jakan.uirfood.bean.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuDao extends MongoRepository<Menu, String> {
}
