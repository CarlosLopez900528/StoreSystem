package com.example.store.service.impl;

import com.example.store.model.Product;
import com.example.store.repository.IProductRepository;
import com.example.store.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends CRUDImpl<Product, Integer> implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    protected JpaRepository<Product, Integer> getRepository() {
        return productRepository;
    }
}
