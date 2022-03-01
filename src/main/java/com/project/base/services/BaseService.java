package com.project.base.services;


import com.project.base.entity.BaseEntity;
import com.project.base.repositorie.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Elí.Arizpe on 01/04/2020.
 */

public abstract class BaseService<T extends BaseRepository, E extends BaseEntity> {
    @Autowired
    protected T repository;

    public E findById(long id) {
        return (E) repository.findById(id);
    }

    public E findByUuId(String uuid) {
        return (E) repository.findByUuid(uuid);
    }

    public List<E> findAll() {
        return (List<E>) repository.findAll();
    }

    public E create(E entity) {
        return (E) repository.save(entity);
    }

    public E update(E entity) {
        return (E) repository.save(entity);
    }

    public E delete(long id) {
        E entity = findById(id);
        entity.setSoftDelete(true);
        return (E) repository.save(entity);
    }

    public E delete(String uuid) {
        E entity = findByUuId(uuid);
        entity.setSoftDelete(true);
        return (E) repository.save(entity);
    }

    public E delete(E entity) {
        entity.setSoftDelete(true);
        return (E) repository.save(entity);
    }

    public List<E> findByCreatedAt(LocalDateTime localDateTime){
        return repository.findByCreatedAt(localDateTime);
    }
}