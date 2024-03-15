package com.jakan.uirfood.service.Impl;

import com.jakan.uirfood.bean.User;
import com.jakan.uirfood.dao.UserDao;
import com.jakan.uirfood.dto.UserDto;
import com.jakan.uirfood.exception.DuplicatedIdException;
import com.jakan.uirfood.exception.ResourceNotFoundException;
import com.jakan.uirfood.service.facade.UserService;
import com.jakan.uirfood.transformer.UserTransformer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired private UserDao userDao;
    @Autowired private UserTransformer userTransformer;

    @Override
    public List<UserDto> findAll() {
        List<User> users=userDao.findAll();
        return userTransformer.toDto(users);
    }

    @Override
    public UserDto findById(String id) {
        User user=userDao.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User","Id", id)
        );
        return userTransformer.toDto(user);

    }

    @Override
    public UserDto save(UserDto dto) {
        UserDto existUser=findById(dto.id());
        if(existUser != null){
            new DuplicatedIdException("User", "Id", existUser.id());
        }
        User entity=userTransformer.toEntity(dto);
        User savedUser=userDao.save(entity);
        dto=userTransformer.toDto(savedUser);
        return dto;
    }

    @Override
    public List<UserDto> save(List<UserDto> dtos) {
        if(dtos != null && !dtos.isEmpty()){
            return dtos.stream().map(this::save).toList();
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<UserDto> updateById(String id) {
        UserDto foundedUser=findById(id);
        if(foundedUser != null) {
            User user = userTransformer.toEntity(foundedUser);
            User updatedUser = userDao.save(user);
            return Optional.of(userTransformer.toDto(updatedUser));
        }
        return Optional.empty();
    }

    @Override
    public int deleteById(String id) {
        UserDto foundedUser=findById(id);
        User user=userTransformer.toEntity(foundedUser);
        userDao.deleteById(user.getId());
        return 1;
    }

}
