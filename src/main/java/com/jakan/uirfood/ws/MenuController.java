package com.jakan.uirfood.ws;

import com.jakan.uirfood.dto.MenuDto;
import com.jakan.uirfood.service.Impl.MenuServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/menus")
public class MenuController {
    private MenuServiceImpl menuService;

    public MenuController(MenuServiceImpl menuService){
        this.menuService=menuService;
    }

    @GetMapping("/")
    public ResponseEntity<List<MenuDto>> findAll(){
        return ResponseEntity.ok(menuService.findAll());
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<MenuDto> findById(@PathVariable String id){
        return ResponseEntity.ok(menuService.findById(id));
    }
    @PostMapping("/")
    public ResponseEntity<MenuDto> save(@RequestBody MenuDto dto){
        return new ResponseEntity<>(menuService.save(dto), HttpStatus.CREATED);
    }
    @PostMapping("/list/")
    public ResponseEntity<List<MenuDto>> save(@RequestBody List<MenuDto> dtos){
        return new ResponseEntity<>(menuService.save(dtos), HttpStatus.CREATED);
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
