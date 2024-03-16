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
public class MenuServiceImpl implements MenuService {
    @Autowired private MenuDao menuDao;
    @Autowired private MenuTransformer menuTransformer;
    @Autowired private CategorieServiceImpl categorieService;
    @Autowired private CategorieTransformer categorieTransformer;

    @Override
    public CompletableFuture<List<MenuDto>> findAll() {
        long startTime=System.currentTimeMillis();
        log.info("get list of users by "+Thread.currentThread().getName());
        List<Menu> menus=menuDao.findAll();
        long endTime=System.currentTimeMillis();
        log.info("Total time {}",(endTime - startTime));
        return CompletableFuture.completedFuture(menuTransformer.toDto(menus));
    }

    @Override
    public MenuDto findById(String id) {
        Menu foundedMenu=menuDao.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Menu", "Id", id));
        return menuTransformer.toDto(foundedMenu);
    }

    @Override
    public CompletableFuture<MenuDto> save(MenuDto dto) {
        long startTime=System.currentTimeMillis();
        MenuDto existMenu=findById(dto.id());
        if(existMenu != null){
            new DuplicatedIdException("Menu", "Id", existMenu.id());
        }
        Menu transformedMenu=menuTransformer.toEntity(dto);
        log.info("saving user by "+Thread.currentThread().getName());
        Menu savedMenu=menuDao.save(transformedMenu);
        long endTime=System.currentTimeMillis();
        log.info("Total time {}",(endTime - startTime));
        return CompletableFuture.completedFuture(menuTransformer.toDto(savedMenu));
    }

    @Override
    public CompletableFuture<List<MenuDto>> save(List<MenuDto> dtos) {
        long startTime=System.currentTimeMillis();
        if(dtos != null || !dtos.isEmpty()){
            log.info("saving list of users of size {}", dtos.size()+" by "+Thread.currentThread().getName(), Thread.currentThread().getName());
            dtos.stream().map(this::save).toList();
            long endTime=System.currentTimeMillis();
            log.info("Total time {}",(endTime - startTime));
            return CompletableFuture.completedFuture(dtos);
        }
        return CompletableFuture.completedFuture(Collections.emptyList());
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
