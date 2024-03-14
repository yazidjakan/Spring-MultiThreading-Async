package com.jakan.uirfood.dao;

import com.jakan.uirfood.bean.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends MongoRepository<User, String> {
}
