package com.jakan.uirfood.transformer;

import com.jakan.uirfood.dto.MenuDto;
import com.jakan.uirfood.bean.Menu;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MenuTransformer extends AbstractTransformer<Menu, MenuDto> {
    @Override
    public Menu toEntity(MenuDto dto) {
        if(dto == null ){
            return null;
        }else{
            Menu entity=new Menu();
            entity.setId(dto.id());
            entity.setCategories(dto.categories());
            return entity;
        }

    }

    @Override
    public MenuDto toDto(Menu entity) {
        if(entity == null) {
            return null;
        }else{
            MenuDto dto=new MenuDto(
                    entity.getId(),
                    entity.getCategories()
            );
            return dto;
        }
    }

}
