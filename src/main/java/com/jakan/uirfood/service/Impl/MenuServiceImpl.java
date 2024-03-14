package com.jakan.uirfood.service.Impl;

import com.jakan.uirfood.bean.Categorie;
import com.jakan.uirfood.bean.Menu;
import com.jakan.uirfood.dao.MenuDao;
import com.jakan.uirfood.dto.CategorieDto;
import com.jakan.uirfood.dto.MenuDto;
import com.jakan.uirfood.exception.DuplicatedIdException;
import com.jakan.uirfood.exception.ResourceNotFoundException;
import com.jakan.uirfood.service.facade.MenuService;
import com.jakan.uirfood.transformer.CategorieTransformer;
import com.jakan.uirfood.transformer.MenuTransformer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService {
    @Autowired private MenuDao menuDao;
    @Autowired private MenuTransformer menuTransformer;
    @Autowired private CategorieServiceImpl categorieService;
    @Autowired private CategorieTransformer categorieTransformer;

    @Override
    public List<MenuDto> findAll() {
        List<Menu> menus=menuDao.findAll();
        return menuTransformer.toDto(menus);
    }

    @Override
    public MenuDto findById(String id) {
        Menu foundedMenu=menuDao.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Menu", "Id", id));
        return menuTransformer.toDto(foundedMenu);
    }

    private void findCategories(Menu menu){
        List<CategorieDto> foundedCategories=categorieService.findAll();
        if(foundedCategories != null && !foundedCategories.isEmpty()){
            List<Categorie> transformedCategories=categorieTransformer.toEntity(foundedCategories);
            menu.setCategories(transformedCategories);
        }
    }
    private void prepareSave(Menu menu){
        findCategories(menu);
    }
    @Override
    public MenuDto save(MenuDto dto) {
        MenuDto existMenu=findById(dto.id());
        if(existMenu != null){
            new DuplicatedIdException("Menu", "Id", existMenu.id());
        }
        Menu transformedMenu=menuTransformer.toEntity(dto);
        Menu savedMenu=menuDao.save(transformedMenu);
        return menuTransformer.toDto(savedMenu);
    }

    @Override
    public List<MenuDto> save(List<MenuDto> dtos) {
        if(dtos != null || !dtos.isEmpty()){
            return dtos.stream().map(this::save).toList();
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<MenuDto> updateById(String id) {
        MenuDto foundedMenu=findById(id);
        if(foundedMenu != null) {
            Menu transformedMenu = menuTransformer.toEntity(foundedMenu);
            Menu savedMenu = menuDao.save(transformedMenu);
            return Optional.of(menuTransformer.toDto(savedMenu));
        }
        return Optional.empty();
    }

    @Override
    public int deleteById(String id) {
        MenuDto foundedMenu=findById(id);
        menuDao.deleteById(foundedMenu.id());
        return 1;
    }
}
