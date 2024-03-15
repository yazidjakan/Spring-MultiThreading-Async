package com.jakan.uirfood.ws;

import com.jakan.uirfood.dto.CategorieDto;
import com.jakan.uirfood.service.Impl.CategorieServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/categories")
public class CategorieController {
    private CategorieServiceImpl categorieService;

    public CategorieController(CategorieServiceImpl categorieService){
        this.categorieService=categorieService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CategorieDto>> findAll(){
        return ResponseEntity.ok(categorieService.findAll());
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<CategorieDto> findById(@PathVariable String id){
        return ResponseEntity.ok(categorieService.findById(id));
    }
    @PostMapping("/")
    public ResponseEntity<CategorieDto> save(@RequestBody CategorieDto dto){
        return new ResponseEntity<>(categorieService.save(dto), HttpStatus.CREATED);
    }
    @PostMapping("/list/")
    public ResponseEntity<List<CategorieDto>> save(@RequestBody List<CategorieDto> dtos){
        return new ResponseEntity<>(categorieService.save(dtos), HttpStatus.CREATED);
    }
    @PutMapping("/id/{id}")
    public ResponseEntity<CategorieDto> updateById(@PathVariable String id){
        Optional<CategorieDto> categorieDtoOptional=categorieService.updateById(id);
        if(categorieDtoOptional.isPresent()){
            return ResponseEntity.ok(categorieDtoOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Integer> deleteById(@PathVariable String id){
        return ResponseEntity.ok(categorieService.deleteById(id));
    }
}
