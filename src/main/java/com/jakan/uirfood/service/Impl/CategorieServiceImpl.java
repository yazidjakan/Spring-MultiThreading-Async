package com.jakan.uirfood.service.Impl;

import com.jakan.uirfood.bean.Categorie;
import com.jakan.uirfood.dao.CategorieDao;
import com.jakan.uirfood.dto.CategorieDto;
import com.jakan.uirfood.exception.DuplicatedIdException;
import com.jakan.uirfood.exception.ResourceNotFoundException;
import com.jakan.uirfood.service.facade.CategorieService;
import com.jakan.uirfood.transformer.CategorieTransformer;
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
public class CategorieServiceImpl implements CategorieService {
    @Autowired private CategorieDao categorieDao;
    @Autowired private CategorieTransformer categorieTransformer;
    @Autowired private RepasServiceImpl repasService;
    @Autowired private RepasTransformer repasTransformer;

    @Override
    public CompletableFuture<List<CategorieDto>> findAll() {
        long startTime=System.currentTimeMillis();
        log.info("get list of users by "+Thread.currentThread().getName());
        List<Categorie> categories=categorieDao.findAll();
        long endTime=System.currentTimeMillis();
        log.info("Total time {}",(endTime - startTime));
        return CompletableFuture.completedFuture(categorieTransformer.toDto(categories));
    }

    @Override
    public CategorieDto findById(String id) {
        Categorie foundedCategorie=categorieDao.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Categorie", "Id", id));
        return categorieTransformer.toDto(foundedCategorie);

    }

    @Override
    public CompletableFuture<CategorieDto> save(CategorieDto dto) {
        CategorieDto foundedCategorie=findById(dto.id());
        if(foundedCategorie != null){
            new DuplicatedIdException("Categorie", "Id", foundedCategorie.id());
        }
        Categorie transformedCategorie=categorieTransformer.toEntity(dto);
        Categorie savedCategorie=categorieDao.save(transformedCategorie);
        return CompletableFuture.completedFuture(categorieTransformer.toDto(savedCategorie));
    }

    @Override
    public CompletableFuture<List<CategorieDto>> save(List<CategorieDto> dtos) {
        long startTime=System.currentTimeMillis();
        if(dtos == null || dtos.isEmpty()){
            log.info("saving list of users of size {}", dtos.size()+" by "+Thread.currentThread().getName(),Thread.currentThread().getName());
            dtos.stream().map(this::save).toList();
            long endTime=System.currentTimeMillis();
            log.info("Total time {}",(endTime - startTime));
            return CompletableFuture.completedFuture(dtos);
        }
        return CompletableFuture.completedFuture(Collections.emptyList());
    }

    @Override
    public Optional<CategorieDto> updateById(String id) {
        CategorieDto foundedCategorie=findById(id);
        if(foundedCategorie != null) {
            Categorie transformedCategorie = categorieTransformer.toEntity(foundedCategorie);
            Categorie savedCategorie = categorieDao.save(transformedCategorie);
            return Optional.of(categorieTransformer.toDto(savedCategorie));
        }
        return Optional.empty();
    }

    @Override
    public int deleteById(String id) {
        CategorieDto foundedCategorie=findById(id);
        categorieDao.deleteById(foundedCategorie.id());
        return 1;
    }
}
