package com.jakan.uirfood.transformer;

import com.jakan.uirfood.dto.CategorieDto;
import com.jakan.uirfood.bean.Categorie;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategorieTransformer extends AbstractTransformer<Categorie, CategorieDto> {
    @Override
    public Categorie toEntity(CategorieDto dto) {
        if(dto == null) {
            return null;
        }else{
            Categorie entity=new Categorie();
            entity.setId(dto.id());
            entity.setNom(dto.nom());
            entity.setRepasList(dto.repasList());
            return entity;
        }
    }

    @Override
    public CategorieDto toDto(Categorie entity) {
        if(entity == null){
            return null;
        }else{
            CategorieDto dto=new CategorieDto(
                    entity.getId(),
                    entity.getNom(),
                    entity.getRepasList()
            );
            return dto;
        }

    }

}
