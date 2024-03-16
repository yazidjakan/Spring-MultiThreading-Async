package com.jakan.uirfood.ws;

import com.jakan.uirfood.dto.CategorieDto;
import com.jakan.uirfood.service.Impl.CategorieServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/categories")
public class CategorieController {
    private CategorieServiceImpl categorieService;

    public CategorieController(CategorieServiceImpl categorieService){
        this.categorieService=categorieService;
    }

    @GetMapping("/")
    public CompletableFuture<ResponseEntity<List<CategorieDto>>> findAll(){
        CompletableFuture<List<CategorieDto>> categories=categorieService.findAll();
        return categories.thenApply(ResponseEntity::ok);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<CategorieDto> findById(@PathVariable String id){
        return ResponseEntity.ok(categorieService.findById(id));
    }
    @PostMapping("/")
    public CompletableFuture<ResponseEntity<CategorieDto>> save(@RequestBody CategorieDto dto){
        CompletableFuture<CategorieDto> savedCategorie=categorieService.save(dto);
        return savedCategorie.thenApply(categorie -> ResponseEntity.status(HttpStatus.CREATED).body(categorie));
    }
    @PostMapping("/list/")
    public CompletableFuture<ResponseEntity<List<CategorieDto>>> save(@RequestBody List<CategorieDto> dtos){
        CompletableFuture<List<CategorieDto>> savedCategorieList=categorieService.save(dtos);
        return savedCategorieList.thenApply(categories -> ResponseEntity.status(HttpStatus.CREATED).body(categories));
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
