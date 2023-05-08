package com.example.store.service;

import java.util.List;

public interface ICRUD<T,ID> {

    T save(T t) throws Exception;
    T update(T t) throws Exception;
    List<T> listAll() throws Exception;
    T listById(ID id) throws Exception;
    void delete(ID id) throws Exception;
}
