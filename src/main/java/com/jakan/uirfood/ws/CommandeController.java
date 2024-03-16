package com.jakan.uirfood.ws;

import com.jakan.uirfood.dto.CommandeDto;
import com.jakan.uirfood.service.Impl.CommandeServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/commandes")
public class CommandeController {
     private CommandeServiceImpl commandeService;

     public CommandeController(CommandeServiceImpl commandeService){
         this.commandeService=commandeService;
     }
     @GetMapping("/")
     public CompletableFuture<ResponseEntity<List<CommandeDto>>> findAll(){
         CompletableFuture<List<CommandeDto>> commandes=commandeService.findAll();
         return commandes.thenApply(ResponseEntity::ok);
     }

     @GetMapping("/id/{id}")
    public ResponseEntity<CommandeDto> findById(@PathVariable String id){
         return ResponseEntity.ok(commandeService.findById(id));
     }

     @PostMapping("/")
    public CompletableFuture<ResponseEntity<CommandeDto>> save(@RequestBody CommandeDto dto){
         CompletableFuture<CommandeDto> savedCommande=commandeService.save(dto);
         return savedCommande.thenApply(commande -> ResponseEntity.status(HttpStatus.CREATED).body(commande));
     }
     @PostMapping("/list/")
     public CompletableFuture<ResponseEntity<List<CommandeDto>>> save(@RequestBody List<CommandeDto> dtos){
         CompletableFuture<List<CommandeDto>> savedCommandeList=commandeService.save(dtos);
         return savedCommandeList.thenApply(commandes -> ResponseEntity.status(HttpStatus.CREATED).body(commandes));
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
