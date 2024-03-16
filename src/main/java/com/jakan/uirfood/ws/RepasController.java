package com.jakan.uirfood.ws;

import com.jakan.uirfood.dto.RepasDto;

import com.jakan.uirfood.service.Impl.RepasServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/repas")
public class RepasController {
     private RepasServiceImpl repasService;

     public RepasController(RepasServiceImpl repasService){
         this.repasService=repasService;
     }

    @GetMapping("/")
    public CompletableFuture<ResponseEntity<List<RepasDto>>> findAll(){
         CompletableFuture<List<RepasDto>> repasList=repasService.findAll();
        return repasList.thenApply(ResponseEntity::ok);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<RepasDto> findById(@PathVariable String id){
        return ResponseEntity.ok(repasService.findById(id));
    }
    @PostMapping("/")
    public CompletableFuture<ResponseEntity<RepasDto>> save(@RequestBody RepasDto dto){
        CompletableFuture<RepasDto> savedRepas=repasService.save(dto);
        return savedRepas.thenApply(repas -> ResponseEntity.status(HttpStatus.CREATED).body(repas));
    }
    @PostMapping("/list/")
    public CompletableFuture<ResponseEntity<List<RepasDto>>> save(@RequestBody List<RepasDto> dtos){
        CompletableFuture<List<RepasDto>> savedRepasList=repasService.save(dtos);
        return savedRepasList.thenApply(repas -> ResponseEntity.status(HttpStatus.CREATED).body(repas));
    }
    @PutMapping("/id/{id}")
    public ResponseEntity<RepasDto> updateById(@PathVariable String id){
        Optional<RepasDto> repasDtoOptional=repasService.updateById(id);
        if(repasDtoOptional.isPresent()){
            return ResponseEntity.ok(repasDtoOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Integer> deleteById(@PathVariable String id){
        return ResponseEntity.ok(repasService.deleteById(id));
    }
}
