package com.jakan.uirfood.ws;


import com.jakan.uirfood.dto.MenuDto;
import com.jakan.uirfood.service.Impl.MenuServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/menus")
public class MenuController {
    private MenuServiceImpl menuService;

    public MenuController(MenuServiceImpl menuService){
        this.menuService=menuService;
    }

    @GetMapping("/")
    public CompletableFuture<ResponseEntity<List<MenuDto>>> findAll(){
        CompletableFuture<List<MenuDto>> menus=menuService.findAll();
        return menus.thenApply(ResponseEntity::ok);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<MenuDto> findById(@PathVariable String id){
        return ResponseEntity.ok(menuService.findById(id));
    }
    @PostMapping("/")
    public CompletableFuture<ResponseEntity<MenuDto>> save(@RequestBody MenuDto dto){
        CompletableFuture<MenuDto> savedMenu=menuService.save(dto);
        return savedMenu.thenApply(menu -> ResponseEntity.status(HttpStatus.CREATED).body(menu));
    }
    @PostMapping("/list/")
    public CompletableFuture<ResponseEntity<List<MenuDto>>> save(@RequestBody List<MenuDto> dtos){
        CompletableFuture<List<MenuDto>> savedMenuList=menuService.save(dtos);
        return savedMenuList.thenApply(menus -> ResponseEntity.status(HttpStatus.CREATED).body(menus));
    }
    @PutMapping("/")
    public ResponseEntity<MenuDto> updateById(@PathVariable String id){
        Optional<MenuDto> menuDtoOptional=menuService.updateById(id);
        if(menuDtoOptional.isPresent()){
            return ResponseEntity.ok(menuDtoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Integer> deleteById(@PathVariable String id){
        return ResponseEntity.ok(menuService.deleteById(id));
    }
}
