package com.jakan.uirfood.ws;

import com.jakan.uirfood.dto.UserDto;
import com.jakan.uirfood.service.Impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService){
        this.userService=userService;
    }
    @GetMapping("/")
    public CompletableFuture<ResponseEntity<List<UserDto>>> findAll(){
        CompletableFuture<List<UserDto>> users=userService.findAll();
        return users.thenApply(ResponseEntity::ok);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(userService.findById(id));
    }
    @PostMapping("/")
    public CompletableFuture<ResponseEntity<UserDto>> save(@RequestBody UserDto dto){
        CompletableFuture<UserDto> savedUser=userService.save(dto);
        return savedUser.thenApply(user -> ResponseEntity.status(HttpStatus.CREATED).body(user));
    }
    @PostMapping("/list/")
    public CompletableFuture<ResponseEntity<List<UserDto>>> save(@RequestBody List<UserDto> dtos){
        CompletableFuture<List<UserDto>> savedUsers=userService.save(dtos);
        return savedUsers.thenApply(users -> ResponseEntity.status(HttpStatus.CREATED).body(users));
    }
    @PutMapping("/id/{id}")
    public ResponseEntity<UserDto> updateById(@PathVariable String id){
        Optional<UserDto> userDto=userService.updateById(id);
        if(userDto.isPresent()){
            return ResponseEntity.ok(userDto.get());
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Integer> deleteById(@PathVariable String id){
        return ResponseEntity.ok(userService.deleteById(id));
    }
}
