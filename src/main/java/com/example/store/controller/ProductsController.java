package com.example.store.controller;

import com.example.store.dto.ProductDTO;
import com.example.store.dto.ProductRequest;
import com.example.store.exeption.ModelNotFoundException;
import com.example.store.model.Product;
import com.example.store.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    public static final String ID_NOT_FOUND = "ID NOT FOUND ";
    @Autowired
    private IProductService productService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> getAllProducts() throws Exception {
        List<ProductDTO> productDTOs = productService.listAll().stream()
            .map(p -> mapper.map(p, ProductDTO.class))
            .toList();
        return ResponseEntity.status(HttpStatus.OK).body(productDTOs);
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<ProductRequest> getProductById(
        @PathVariable("idProduct") Integer idProduct) throws Exception {
        Product product = productService.listById(idProduct);
        if (product == null) {
            throw new ModelNotFoundException(ID_NOT_FOUND + idProduct);
        }
        return ResponseEntity.status(HttpStatus.OK).body(mapper.map(product, ProductRequest.class));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductRequest productDTO)
        throws Exception {
        Product product = mapper.map(productDTO, Product.class);
        Product savedProduct = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(mapper.map(savedProduct, ProductDTO.class));
    }

    @PutMapping("/{idProduct}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("idProduct") Integer idProduct,
        @RequestBody ProductRequest productDTO) throws Exception {
        Product product = productService.listById(idProduct);
        if (product == null) {
            throw new ModelNotFoundException(ID_NOT_FOUND + productDTO.getId());
        }
        productDTO.setId(product.getId());
        Product newProduct = mapper.map(productDTO, Product.class);
        Product updatedProduct = productService.update(newProduct);
        return ResponseEntity.status(HttpStatus.OK)
            .body(mapper.map(updatedProduct, ProductDTO.class));
    }

    @DeleteMapping("/{idProduct}")
    public ResponseEntity<Void> deleteProductById(@PathVariable("idProduct") Integer idProduct)
        throws Exception {
        Product product = productService.listById(idProduct);
        if (product == null) {
            throw new ModelNotFoundException(ID_NOT_FOUND + idProduct);
        }
        productService.delete(idProduct);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
