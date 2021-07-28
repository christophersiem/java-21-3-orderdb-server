package de.neuefische.java213orderdbserver.service;

import de.neuefische.java213orderdbserver.model.Product;
import de.neuefische.java213orderdbserver.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public Optional<Product> getProductById(String id){
       return productRepository.getProductById(id);
    }

    public List<Product> search(String q) {
        List<Product> matchingProducts = new ArrayList<>();
        for (Product product : getAllProducts()) {
            if(product.getName().contains(q)){
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }
}
