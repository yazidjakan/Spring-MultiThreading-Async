package com.jakan.uirfood.service.Impl;

import com.jakan.uirfood.bean.Commande;
import com.jakan.uirfood.bean.Repas;
import com.jakan.uirfood.dao.CommandeDao;
import com.jakan.uirfood.dto.CommandeDto;
import com.jakan.uirfood.dto.RepasDto;
import com.jakan.uirfood.exception.DuplicatedIdException;
import com.jakan.uirfood.exception.ResourceNotFoundException;
import com.jakan.uirfood.service.facade.CommandeService;
import com.jakan.uirfood.transformer.CommandeTransformer;
import com.jakan.uirfood.transformer.RepasTransformer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
@Slf4j
public class CommandeServiceImpl implements CommandeService {
    @Autowired private CommandeDao commandeDao;
    @Autowired private CommandeTransformer commandeTransformer;
    @Autowired private RepasTransformer repasTransformer;
    @Override
    public CompletableFuture<List<CommandeDto>> findAll() {
        long startTime=System.currentTimeMillis();
        log.info("get list of users by "+Thread.currentThread().getName());
        List<Commande> commandes=commandeDao.findAll();
        long endTime=System.currentTimeMillis();
        log.info("Total time {}",(endTime - startTime));
        return CompletableFuture.completedFuture(commandeTransformer.toDto(commandes));
    }

    @Override
    public CommandeDto findById(String id) {
        Commande foundedCommande=commandeDao.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Commande", "Id", id));
        return commandeTransformer.toDto(foundedCommande);
    }


    @Override
    public CompletableFuture<CommandeDto> save(CommandeDto dto) {
        long startTime=System.currentTimeMillis();
        CommandeDto existCommande=findById(dto.id());
        if(existCommande != null){
            throw new DuplicatedIdException("Commande", "Id", existCommande.id());
        }
        Commande transformedCommande=commandeTransformer.toEntity(dto);
        log.info("saving user by "+Thread.currentThread().getName());
        Commande savedCommande=commandeDao.save(transformedCommande);
        long endTime=System.currentTimeMillis();
        log.info("Total time {}",(endTime - startTime));
        return CompletableFuture.completedFuture(commandeTransformer.toDto(savedCommande));
    }

    @Override
    public CompletableFuture<List<CommandeDto>> save(List<CommandeDto> dtos) {
        long startTime=System.currentTimeMillis();
        if(dtos == null || dtos.isEmpty()){
            log.info("saving list of users of size {}", dtos.size()+" by "+Thread.currentThread().getName(), Thread.currentThread().getName());
            dtos.stream().map(this::save).toList();
            long endTime=System.currentTimeMillis();
            log.info("Total time {}",(endTime - startTime));
            return CompletableFuture.completedFuture(dtos);
        }
        return CompletableFuture.completedFuture(Collections.emptyList());
    }

    @Override
    public Optional<CommandeDto> updateById(String id) {
        CommandeDto foundedCommande=findById(id);
        if(foundedCommande != null){
            Commande transformedCommande=commandeTransformer.toEntity(foundedCommande);
            Commande updatedCommande=commandeDao.save(transformedCommande);
            return Optional.of(commandeTransformer.toDto(updatedCommande));
        }
        return Optional.empty();
    }

    @Override
    public int deleteById(String id) {
        findById(id);
        commandeDao.deleteById(id);
        return 1;
    }
}
