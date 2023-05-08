package com.example.store.controller;

import com.example.store.dto.ProductDTO;
import com.example.store.exeption.ModelNotFoundException;
import com.example.store.model.Product;
import com.example.store.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> getAllProducts() throws Exception {
        return new ResponseEntity<>(productService.listAll()
                .stream()
                .map(p -> mapper.map(p, ProductDTO.class)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("idProduct") Integer idProduct) throws Exception {
        Product product = productService.listById(idProduct);
        if (product == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + idProduct);
        }
        return new ResponseEntity<>(mapper.map(product, ProductDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productDTO) throws Exception {
        Product product = mapper.map(productDTO, Product.class);
        return new ResponseEntity<>(mapper.map(productService.save(product), ProductDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO) throws Exception {
        Product product = productService.listById(productDTO.getIdProduct());
        if (product == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + productDTO.getIdProduct());
        }
        productDTO.setIdProduct(product.getIdProduct());
        return new ResponseEntity<>(mapper.map(productService.update(product), ProductDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{idProduct}")
    public ResponseEntity<Void> deleteProductById(@PathVariable("idProduct") Integer idProduct) throws Exception {
        Product product = productService.listById(idProduct);
        if (product == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + idProduct);
        }
        productService.delete(idProduct);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
