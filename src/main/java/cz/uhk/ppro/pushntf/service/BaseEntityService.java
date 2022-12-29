package cz.uhk.ppro.pushntf.service;

import cz.uhk.ppro.pushntf.exception.NotFoundException;

import java.util.List;

public interface BaseEntityService<T> {
    List<T> findAll();
    T findByUUID(String uuid) throws NotFoundException;
    T create(T t);
    T update(T t);
    void deleteByUUID(String uuid);
}
