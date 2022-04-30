package org.sid.ecom.web;

import org.sid.ecom.dtos.ProductDTO;
import org.sid.ecom.entities.Product;
import org.sid.ecom.repositories.ProductRepository;
import org.sid.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductRestController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @GetMapping(path = "/products")
    public List<ProductDTO> productList(){
        return productService.listProducts();
    }

    @GetMapping(path = "/products/{id}")
    public Product getProducts(@PathVariable(name="id") String id){
        return productRepository.findById(id).get();
    }

    @PostMapping(path = "/products")
    public Product saveProduct(@RequestBody Product product){
        return productRepository.save(product);
    }

    //Put est modifié toutes les champs
    //Patch modifie just le champs indiqué
    @PutMapping("/products/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable String id){
        product.setId(id);
        return productRepository.save(product);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable String id){
        productRepository.deleteById(id);
    }
}
