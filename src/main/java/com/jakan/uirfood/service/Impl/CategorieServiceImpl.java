package com.jakan.uirfood.service.Impl;

import com.jakan.uirfood.bean.Categorie;
import com.jakan.uirfood.bean.Repas;
import com.jakan.uirfood.dao.CategorieDao;
import com.jakan.uirfood.dto.CategorieDto;
import com.jakan.uirfood.dto.RepasDto;
import com.jakan.uirfood.exception.DuplicatedIdException;
import com.jakan.uirfood.exception.ResourceNotFoundException;
import com.jakan.uirfood.service.facade.CategorieService;
import com.jakan.uirfood.transformer.CategorieTransformer;
import com.jakan.uirfood.transformer.RepasTransformer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategorieServiceImpl implements CategorieService {
    @Autowired private CategorieDao categorieDao;
    @Autowired private CategorieTransformer categorieTransformer;
    @Autowired private RepasServiceImpl repasService;
    @Autowired private RepasTransformer repasTransformer;

    @Override
    public List<CategorieDto> findAll() {
        List<Categorie> categories=categorieDao.findAll();
        return categorieTransformer.toDto(categories);
    }

    @Override
    public CategorieDto findById(String id) {
        Categorie foundedCategorie=categorieDao.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Categorie", "Id", id));
        return categorieTransformer.toDto(foundedCategorie);

    }

    private void findRepas(Categorie categorie) {
        List<RepasDto> foundedRepas = repasService.findAll();
        if (foundedRepas != null && !foundedRepas.isEmpty()) {
            List<Repas> transformedRepas=repasTransformer.toEntity(foundedRepas);
            categorie.setRepasList(transformedRepas);
        }
    }
    private void prepareSave(Categorie categorie){
        findRepas(categorie);
    }
    @Override
    public CategorieDto save(CategorieDto dto) {
        CategorieDto foundedCategorie=findById(dto.id());
        if(foundedCategorie != null){
            new DuplicatedIdException("Categorie", "Id", foundedCategorie.id());
        }
        Categorie transformedCategorie=categorieTransformer.toEntity(dto);
        Categorie savedCategorie=categorieDao.save(transformedCategorie);
        return categorieTransformer.toDto(savedCategorie);
    }

    @Override
    public List<CategorieDto> save(List<CategorieDto> dtos) {
        if(dtos == null || dtos.isEmpty()){
            return dtos.stream().map(this::save).toList();
        }
        return Collections.emptyList();
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
