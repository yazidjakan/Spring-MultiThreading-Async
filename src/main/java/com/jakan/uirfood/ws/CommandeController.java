package com.jakan.uirfood.ws;

import com.jakan.uirfood.dto.CommandeDto;
import com.jakan.uirfood.service.Impl.CommandeServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/commandes")
public class CommandeController {
     private CommandeServiceImpl commandeService;

     public CommandeController(CommandeServiceImpl commandeService){
         this.commandeService=commandeService;
     }
     @GetMapping("/")
     public ResponseEntity<List<CommandeDto>> findAll(){
         return ResponseEntity.ok(commandeService.findAll());
     }

     @GetMapping("/id/{id}")
    public ResponseEntity<CommandeDto> findById(@PathVariable String id){
         return ResponseEntity.ok(commandeService.findById(id));
     }

     @PostMapping("/")
    public ResponseEntity<CommandeDto> save(@RequestBody CommandeDto dto){
         return new ResponseEntity<>(commandeService.save(dto), HttpStatus.CREATED);
     }
     @PostMapping("/list/")
     public ResponseEntity<List<CommandeDto>> save(@RequestBody List<CommandeDto> dtos){
         return new ResponseEntity<>(commandeService.save(dtos), HttpStatus.CREATED);
     }
     @PutMapping("/id/{id}")
    public ResponseEntity<CommandeDto> updateById(@PathVariable String id){
         Optional<CommandeDto> commandeDtoOptional=commandeService.updateById(id);
         if(commandeDtoOptional.isPresent()){
             return ResponseEntity.ok(commandeDtoOptional.get());
         }else{
             return ResponseEntity.notFound().build();
         }
     }
     @DeleteMapping("/id/{id}")
    public ResponseEntity<Integer> deleteById(@PathVariable String id){
         return ResponseEntity.ok(commandeService.deleteById(id));
     }

}
