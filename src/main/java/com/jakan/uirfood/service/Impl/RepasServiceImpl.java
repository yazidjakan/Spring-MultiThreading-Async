package com.jakan.uirfood.service.Impl;

import com.jakan.uirfood.bean.Repas;
import com.jakan.uirfood.dao.RepasDao;
import com.jakan.uirfood.dto.RepasDto;
import com.jakan.uirfood.exception.DuplicatedIdException;
import com.jakan.uirfood.exception.ResourceNotFoundException;
import com.jakan.uirfood.service.facade.RepasService;
import com.jakan.uirfood.transformer.RepasTransformer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RepasServiceImpl implements RepasService {
    @Autowired private RepasDao repasDao;
    @Autowired private RepasTransformer repasTransformer;

    @Override
    public List<RepasDto> findAll() {
        List<Repas> repasList=repasDao.findAll();
        return repasTransformer.toDto(repasList);
    }

    @Override
    public RepasDto findById(String id) {
        Repas foundedRepas=repasDao.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Repas", "Id", id));
        return repasTransformer.toDto(foundedRepas);
    }

    @Override
    public RepasDto save(RepasDto dto) {
        RepasDto existRepas=findById(dto.id());
        if(existRepas != null){
            new DuplicatedIdException("Repas", "Id", existRepas.id());
        }
        Repas repas=repasTransformer.toEntity(dto);
        Repas savedRepas=repasDao.save(repas);
        return repasTransformer.toDto(savedRepas);
    }

    @Override
    public List<RepasDto> save(List<RepasDto> dtos) {
        if(dtos != null && !dtos.isEmpty()){
            return dtos.stream().map(this::save).toList();
        }
        return Collections.emptyList();
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
