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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommandeServiceImpl implements CommandeService {
    @Autowired private CommandeDao commandeDao;
    @Autowired private CommandeTransformer commandeTransformer;
    @Autowired private RepasTransformer repasTransformer;
    @Override
    public List<CommandeDto> findAll() {
        List<Commande> commandes=commandeDao.findAll();
        return commandeTransformer.toDto(commandes);
    }

    @Override
    public CommandeDto findById(String id) {
        Commande foundedCommande=commandeDao.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Commande", "Id", id));
        return commandeTransformer.toDto(foundedCommande);
    }


    @Override
    public CommandeDto save(CommandeDto dto) {
        CommandeDto existCommande=findById(dto.id());
        if(existCommande != null){
            throw new DuplicatedIdException("Commande", "Id", existCommande.id());
        }
        Commande transformedCommande=commandeTransformer.toEntity(dto);
        Commande savedCommande=commandeDao.save(transformedCommande);
        return commandeTransformer.toDto(savedCommande);
    }

    @Override
    public List<CommandeDto> save(List<CommandeDto> dtos) {
        if(dtos == null || dtos.isEmpty()){
            return dtos.stream().map(this::save).toList();
        }
        return Collections.emptyList();
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
