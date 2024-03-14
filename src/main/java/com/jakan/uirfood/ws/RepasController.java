package com.jakan.uirfood.ws;

import com.jakan.uirfood.dto.RepasDto;

import com.jakan.uirfood.service.Impl.RepasServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/repas")
public class RepasController {
    @Autowired private RepasServiceImpl repasService;

    @GetMapping("/")
    public ResponseEntity<List<RepasDto>> findAll(){
        return ResponseEntity.ok(repasService.findAll());
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<RepasDto> findById(@PathVariable String id){
        return ResponseEntity.ok(repasService.findById(id));
    }
    @PostMapping("/")
    public ResponseEntity<RepasDto> save(@RequestBody RepasDto dto){
        return new ResponseEntity<>(repasService.save(dto), HttpStatus.CREATED);
    }
    @PostMapping("/list/")
    public ResponseEntity<List<RepasDto>> save(@RequestBody List<RepasDto> dtos){
        return new ResponseEntity<>(repasService.save(dtos), HttpStatus.CREATED);
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
