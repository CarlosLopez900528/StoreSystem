package com.example.store.service.impl;

import com.example.store.service.ICRUD;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class CRUDImpl<T,ID> implements ICRUD<T,ID> {

    protected abstract JpaRepository<T,ID> getRepository();
    @Override
    public T save(T t) throws Exception {
        return getRepository().save(t);
    }

    @Override
    public T update(T t) throws Exception {
        return getRepository().save(t);
    }

    @Override
    public List<T> listAll() throws Exception {
        return getRepository().findAll();
    }

    @Override
    public T listById(ID id) throws Exception {
        return getRepository().findById(id).orElse(null);
    }

    @Override
    public void delete(ID id) throws Exception {
        getRepository().deleteById(id);
    }
}
