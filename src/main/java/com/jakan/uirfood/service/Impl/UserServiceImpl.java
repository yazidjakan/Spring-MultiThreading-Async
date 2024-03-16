package com.jakan.uirfood.service.Impl;

import com.jakan.uirfood.bean.User;
import com.jakan.uirfood.dao.UserDao;
import com.jakan.uirfood.dto.UserDto;
import com.jakan.uirfood.exception.DuplicatedIdException;
import com.jakan.uirfood.exception.ResourceNotFoundException;
import com.jakan.uirfood.service.facade.UserService;
import com.jakan.uirfood.transformer.UserTransformer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired private UserDao userDao;
    @Autowired private UserTransformer userTransformer;

    @Override
    public CompletableFuture<List<UserDto>> findAll() {
        long startTime=System.currentTimeMillis();
        log.info("get list of users by "+Thread.currentThread().getName());
        List<User> users=userDao.findAll();
        long endTime=System.currentTimeMillis();
        log.info("Total time {}",(endTime - startTime));
        return CompletableFuture.completedFuture(userTransformer.toDto(users));
    }

    @Override
    public UserDto findById(String id) {
        User user=userDao.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User","Id", id)
        );
        return userTransformer.toDto(user);

    }

    @Override
    public CompletableFuture<UserDto> save(UserDto dto) {
        UserDto existUser=findById(dto.id());
        if(existUser != null){
            new DuplicatedIdException("User", "Id", existUser.id());
        }
        User entity=userTransformer.toEntity(dto);
        User savedUser=userDao.save(entity);
        dto=userTransformer.toDto(savedUser);
        return CompletableFuture.completedFuture(dto);
    }

    @Override
    public CompletableFuture<List<UserDto>> save(List<UserDto> dtos) {
        long startTime=System.currentTimeMillis();
        if(dtos != null && !dtos.isEmpty()){
            log.info("saving list of users of size {}", dtos.size() +" by "+ Thread.currentThread().getName(), "{}", Thread.currentThread().getName());
            dtos.stream().map(this::save).toList();
            long endTime=System.currentTimeMillis();
            log.info("Total time {}",(endTime - startTime));
            return CompletableFuture.completedFuture(dtos);
        }
        return CompletableFuture.completedFuture(Collections.emptyList());
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
