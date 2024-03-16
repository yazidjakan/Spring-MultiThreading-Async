package com.jakan.uirfood.service.Impl;

import com.jakan.uirfood.bean.Repas;
import com.jakan.uirfood.dao.RepasDao;
import com.jakan.uirfood.dto.RepasDto;
import com.jakan.uirfood.exception.DuplicatedIdException;
import com.jakan.uirfood.exception.ResourceNotFoundException;
import com.jakan.uirfood.service.facade.RepasService;
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
public class RepasServiceImpl implements RepasService {
    @Autowired private RepasDao repasDao;
    @Autowired private RepasTransformer repasTransformer;

    @Override
    public CompletableFuture<List<RepasDto>> findAll() {
        long startTime=System.currentTimeMillis();
        log.info("get list of users by "+Thread.currentThread().getName());
        List<Repas> repasList=repasDao.findAll();
        long endTime=System.currentTimeMillis();
        log.info("Total time {}",(endTime - startTime));
        return CompletableFuture.completedFuture(repasTransformer.toDto(repasList));
    }

    @Override
    public RepasDto findById(String id) {
        Repas foundedRepas=repasDao.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Repas", "Id", id));
        return repasTransformer.toDto(foundedRepas);
    }

    @Override
    public CompletableFuture<RepasDto> save(RepasDto dto) {
        long startTime=System.currentTimeMillis();
        RepasDto existRepas=findById(dto.id());
        if(existRepas != null){
            new DuplicatedIdException("Repas", "Id", existRepas.id());
        }
        Repas repas=repasTransformer.toEntity(dto);
        log.info("saving user by "+Thread.currentThread().getName());
        Repas savedRepas=repasDao.save(repas);
        long endTime=System.currentTimeMillis();
        log.info("Total time {}",(endTime - startTime));
        return CompletableFuture.completedFuture(repasTransformer.toDto(savedRepas));
    }

    @Override
    public CompletableFuture<List<RepasDto>> save(List<RepasDto> dtos) {
        long startTime=System.currentTimeMillis();
        if(dtos != null && !dtos.isEmpty()){
            log.info("Saving list of users of size {} ",dtos.size()+" by "+Thread.currentThread().getName(), Thread.currentThread().getName());
            dtos.stream().map(this::save).toList();
            long endTime=System.currentTimeMillis();
            log.info("Total time {}",(endTime - startTime));
            return CompletableFuture.completedFuture(dtos);
        }
        return CompletableFuture.completedFuture(Collections.emptyList());
    }

    @Override
    public Optional<RepasDto> updateById(String id) {
        RepasDto foundedRepas=findById(id);
        if(foundedRepas != null) {
            Repas repas = repasTransformer.toEntity(foundedRepas);
            Repas savedRepas = repasDao.save(repas);
            return Optional.of(repasTransformer.toDto(savedRepas));
        }
        return Optional.empty();
    }

    @Override
    public int deleteById(String id) {
        findById(id);
        repasDao.deleteById(id);
        return 1;
    }
}
