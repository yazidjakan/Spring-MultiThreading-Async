package com.jakan.uirfood.ws;

import com.jakan.uirfood.dto.UserDto;
import com.jakan.uirfood.service.Impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService){
        this.userService=userService;
    }
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(userService.findById(id));
    }
    @PostMapping("/")
    public ResponseEntity<UserDto> save(@RequestBody UserDto dto){
        return new ResponseEntity<>(userService.save(dto) ,HttpStatus.CREATED);
    }
    @PostMapping("/list/")
    public ResponseEntity<List<UserDto>> save(@RequestBody List<UserDto> dtos){
        return new ResponseEntity<>(userService.save(dtos), HttpStatus.CREATED);
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
