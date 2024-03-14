package com.jakan.uirfood.transformer;

import com.jakan.uirfood.dto.RepasDto;
import com.jakan.uirfood.bean.Repas;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RepasTransformer extends AbstractTransformer<Repas, RepasDto> {
    @Override
    public Repas toEntity(RepasDto dto) {
        if(dto==null){
            return null;
        }else{
            Repas entity=new Repas();
            entity.setId(dto.id());
            entity.setLibelle(dto.libelle());
            entity.setDescription(dto.description());
            entity.setPrix(dto.prix());
            entity.setNote(dto.note());
            entity.setImage(dto.image());
            return entity;
        }

    }

    @Override
    public RepasDto toDto(Repas entity) {
        if (entity == null){
            return null;
        }else{
            RepasDto dto=new RepasDto(
                    entity.getId(),
                    entity.getLibelle(),
                    entity.getDescription(),
                    entity.getPrix(),
                    entity.getImage(),
                    entity.getNote(),
                    entity.getCategorie()
            );
            return dto;
        }
    }

}
