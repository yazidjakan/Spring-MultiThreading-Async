package com.jakan.uirfood.service.facade;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface AbstractService <D,I>{
    CompletableFuture<List<D>> findAll();
    D findById(I id);
    CompletableFuture<D> save(D dto);
    CompletableFuture<List<D>> save(List<D> dtos);
    Optional<D> updateById(I id);
    int deleteById(I id);

}
