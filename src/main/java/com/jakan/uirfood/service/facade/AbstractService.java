package com.jakan.uirfood.service.facade;

import java.util.List;
import java.util.Optional;

public interface AbstractService <D,I>{
    List<D> findAll();
    D findById(I id);
    D save(D dto);
    List<D> save(List<D> dtos);
    Optional<D> updateById(I id);
    int deleteById(I id);

}
