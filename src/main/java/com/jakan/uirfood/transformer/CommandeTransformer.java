package com.jakan.uirfood.transformer;

import com.jakan.uirfood.bean.Commande;
import com.jakan.uirfood.dto.CommandeDto;
import org.springframework.stereotype.Component;

@Component
public class CommandeTransformer extends AbstractTransformer<Commande, CommandeDto>{
    @Override
    public Commande toEntity(CommandeDto dto) {
        if(dto == null){
            return null;
        }else{
            Commande entity=new Commande();
            entity.setId(dto.id());
            entity.setUser(dto.user());
            entity.setRepas(dto.repas());
            return entity;
        }
    }

    @Override
    public CommandeDto toDto(Commande entity) {
        if(entity == null){
            return null;
        }else{
            CommandeDto dto=new CommandeDto(
                    entity.getId(),
                    entity.getRepas(),
                    entity.getUser()
            );
            return dto;
        }
    }
}
