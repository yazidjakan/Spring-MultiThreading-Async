package com.jakan.uirfood.ws;

import com.jakan.uirfood.dto.CommandeDto;
import com.jakan.uirfood.service.Impl.CommandeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
