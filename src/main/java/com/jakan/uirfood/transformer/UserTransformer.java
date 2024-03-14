package com.jakan.uirfood.transformer;

import com.jakan.uirfood.dto.UserDto;
import com.jakan.uirfood.bean.User;
import org.springframework.stereotype.Component;


@Component
public class UserTransformer extends AbstractTransformer<User, UserDto>{
    @Override
    public User toEntity(UserDto dto) {
        if(dto== null){
            return null;
        }else{
            User entity=new User();
            entity.setId(dto.id());
            entity.setNom(dto.nom());
            entity.setPrenom(dto.prenom());
            entity.setEmail(dto.email());
            entity.setPassword(dto.password());
            entity.setFonction(dto.fonction());
            return entity;
        }
    }

    @Override
    public UserDto toDto(User entity) {
        if(entity==null) {
            return null;
        }else{
            UserDto dto=new UserDto(
                    entity.getId(),
                    entity.getNom(),
                    entity.getPrenom(),
                    entity.getEmail(),
                    entity.getPassword(),
                    entity.getFonction()
            );
            return dto;
        }
    }


}
